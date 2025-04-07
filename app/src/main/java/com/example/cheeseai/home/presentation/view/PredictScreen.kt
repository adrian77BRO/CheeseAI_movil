package com.example.cheeseai.home.presentation.view

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.cheeseai.core.utils.saveBitmapToCache
import com.example.cheeseai.core.utils.sendImageToViewModel
import com.example.cheeseai.home.presentation.viewModel.PredictViewModel

@Composable
fun PredictScreen(viewModel: PredictViewModel = viewModel()) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val prediction by viewModel.predictionResult.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.errorMessage.observeAsState("")

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            sendImageToViewModel(context, it, viewModel)
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val uri = saveBitmapToCache(context, bitmap)
            imageUri = uri
            sendImageToViewModel(context, uri, viewModel)
        } else {
            Log.e("Camera", "No se pudo capturar la imagen.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "Analizar imagen 🧀",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.Yellow)
        )

        Spacer(modifier = Modifier.height(24.dp))

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .padding(24.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { galleryLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
            ) {
                Icon(Icons.Default.Menu, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Galería", color = Color.Black)
            }

            Button(
                onClick = { cameraLauncher.launch(null) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
            ) {
                Icon(Icons.Default.Face, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Cámara", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator(color = Color.Yellow)
        }

        prediction?.let {
            Text(
                "Tipo de queso: ${it.className}",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.Yellow),
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                "Predicción: ${"%.2f".format(it.confidence * 100)}%",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.Yellow)
            )
        }

        if (error.isNotBlank()) {
            Text(
                "Error: $error",
                color = Color.Red,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
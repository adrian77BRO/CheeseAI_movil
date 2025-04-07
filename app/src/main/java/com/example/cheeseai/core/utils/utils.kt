package com.example.cheeseai.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.cheeseai.home.presentation.viewModel.PredictViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

fun getRealPathFromURI(context: Context, contentUri: Uri): String {
    val cursor = context.contentResolver.query(contentUri, null, null, null, null)
    cursor?.use {
        val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
        if (columnIndex != -1) {
            it.moveToFirst()
            return it.getString(columnIndex)
        }
    }
    val inputStream = context.contentResolver.openInputStream(contentUri)
    val tempFile = File(context.cacheDir, "temp_image.jpg")
    inputStream?.use { input ->
        FileOutputStream(tempFile).use { output ->
            input.copyTo(output)
        }
    }
    return tempFile.absolutePath
}

fun sendImageToViewModel(context: Context, uri: Uri, viewModel: PredictViewModel) {
    val file = File(getRealPathFromURI(context, uri))
    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
    viewModel.predict(body)
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    // Genera un nombre único para la imagen basado en la fecha y hora actual
    val fileName = "camera_image_${System.currentTimeMillis()}.jpg"
    val file = File(context.cacheDir, fileName)

    val out = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    out.flush()
    out.close()

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider", // asegúrate de declarar el FileProvider en tu AndroidManifest
        file
    )
}
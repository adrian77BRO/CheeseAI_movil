package com.example.cheeseai.home.presentation

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

fun getRealPathFromURI(context: Context, contentUri: Uri): String {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor? = context.contentResolver.query(contentUri, proj, null, null, null)
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: 0
    val path = cursor?.getString(columnIndex)
    cursor?.close()
    return path ?: ""
}

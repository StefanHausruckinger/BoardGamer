package com.iubh.boardgamer.domain.posts

import android.graphics.Bitmap

interface PostRepository {
    suspend fun uploadPost(imageBitmap: Bitmap, description: String)
}
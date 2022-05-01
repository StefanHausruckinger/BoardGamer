package com.iubh.boardgamer.domain.posts

import android.graphics.Bitmap
import com.iubh.boardgamer.data.remote.PostDataSource

class PostRepositoryImplementation(private val datasource: PostDataSource) : PostRepository {

    override suspend fun uploadPost(
        imageBitmap: Bitmap,
        description: String
    ) = datasource.uploadPost(imageBitmap, description)

}
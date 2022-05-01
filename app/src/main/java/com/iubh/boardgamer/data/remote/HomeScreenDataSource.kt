package com.iubh.boardgamer.data.remote

import com.iubh.boardgamer.core.Resource
import com.iubh.boardgamer.data.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {
    suspend fun getLatestPost(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()

        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let {
                postList.add(it)
            }
        }

        return Resource.Success(postList)
    }
}
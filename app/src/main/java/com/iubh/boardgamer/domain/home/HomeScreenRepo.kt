package com.iubh.boardgamer.domain.home

import com.iubh.boardgamer.core.Resource
import com.iubh.boardgamer.data.Post

interface HomeScreenRepo {
    suspend fun getLatestPost(): Resource<List<Post>>
}
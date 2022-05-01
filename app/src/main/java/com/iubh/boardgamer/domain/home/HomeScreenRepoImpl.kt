package com.iubh.boardgamer.domain.home

import com.iubh.boardgamer.core.Resource
import com.iubh.boardgamer.data.Post
import com.iubh.boardgamer.data.remote.HomeScreenDataSource

class HomeScreenRepoImpl(val dataSource: HomeScreenDataSource) : HomeScreenRepo {

    override suspend fun getLatestPost(): Resource<List<Post>> = dataSource.getLatestPost()
}
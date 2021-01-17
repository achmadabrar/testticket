package com.achmadabrar.tickettest.data.networks

import com.achmadabrar.tickettest.data.models.ResponseSearchUser
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    companion object {
        private const val SEARCH_USER = "search/users"
    }

    @GET(SEARCH_USER)
    suspend fun getUser(@Query("q") q: String? = ""): ResponseSearchUser
}
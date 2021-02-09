package com.example.simpleretrofit.repository

import com.example.simpleretrofit.api.RetrofitInstance
import com.example.simpleretrofit.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}
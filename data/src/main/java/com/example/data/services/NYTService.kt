package com.example.data.services

import com.example.data.model.PopularArticles
import com.example.domain.model.Day
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/{days}.json")
    suspend fun fetchMostPopularArticles(@Path("days") days: Day): PopularArticles

}
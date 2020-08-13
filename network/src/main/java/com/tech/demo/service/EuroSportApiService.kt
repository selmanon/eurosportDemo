package com.tech.demo.service

import com.tech.demo.dto.ResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface EuroSportApiService {

    @GET("/api/json-storage/bin/edfefba")
    fun getNewsList(): Observable<ResponseDTO>
}
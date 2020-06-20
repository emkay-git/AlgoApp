package com.example.algoapp.ui.algo.service

import com.example.algoapp.models.Algo
import retrofit2.Response
import retrofit2.http.GET

interface AlgoService {
    @GET("/b/5eebc97997cb753b4d136713/1")
    suspend fun getAlgoList(): Response<Array<Algo>>

}
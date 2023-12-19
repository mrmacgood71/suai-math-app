package it.macgood.mathanapp.data

import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MathAnalysisApi {
    @GET("/api/v1/demidovich")
    suspend fun getExercises(
        @Query("startId") startId: Int,
        @Query("endId") endId: Int,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 3200
    ): Response<List<ExerciseDto>>
    @GET("/api/v1/demidovich/{id}")
    suspend fun getExercise(@Path("id") id: String): ExerciseDto

    @GET("/api/v1/demidovich/{id}")
    suspend fun getExerciseResponse(@Path("id") id: String): Response<ExerciseDto>

}
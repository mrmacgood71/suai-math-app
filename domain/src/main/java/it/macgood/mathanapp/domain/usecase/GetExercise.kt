package it.macgood.mathanapp.domain.usecase

import it.macgood.core.Resource
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetExercise @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(id: String): Flow<Resource<Exercise>> = flow {
        try {
            emit(Resource.Loading<Exercise>())
            val exercise = repository.getExercise(id)
            emit(Resource.Success<Exercise>(exercise))

        } catch (e: HttpException) {
            emit(Resource.Error<Exercise>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<Exercise>("Internet connection error"))
        }
    }
}
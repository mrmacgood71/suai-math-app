package it.macgood.mathanappkt.ui.handbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.macgood.core.Resource
import it.macgood.mathanapp.data.datasource.ExerciseDto
import it.macgood.mathanapp.data.datasource.toExercise
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import it.macgood.mathanapp.domain.repository.SavedExerciseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    val repository: ExerciseRepository,
    val storage: SavedExerciseRepository
): ViewModel() {

    val range: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val exercises: MutableLiveData<Resource<List<Exercise>>> = MutableLiveData()
    val searchExercises: MutableLiveData<Resource<List<Exercise>>> = MutableLiveData()

    val saved: MutableLiveData<List<Exercise>> = MutableLiveData()

    val page: MutableLiveData<Int> = MutableLiveData()

    init {
        getSearchExercises()
    }

    fun getResponse(startId: Int, endId: Int) = viewModelScope.launch {
        exercises.postValue(Resource.Loading())
        val response = repository.getExercises(startId, endId)
        exercises.postValue(handleExercisesResponse(response))
    }

    fun saveExercise(exercise: ExerciseDto) = viewModelScope.launch { storage.insertExercise(exercise.toExercise()) }
    fun getTasks() = storage.getTasks()
    fun deleteExercise(exercise: ExerciseDto) = viewModelScope.launch { storage.deleteExercises(exercise.toExercise()) }

    private fun getSearchExercises() = viewModelScope.launch {
        searchExercises.postValue(Resource.Loading())
        val response = repository.getExercises(1, 3200)
        searchExercises.postValue(handleExercisesResponse(response))
    }

    private fun handleExercisesResponse(response: List<Exercise>) : Resource<List<Exercise>> {
        if (response.isNotEmpty()) {
            return Resource.Success(response)
        }
        return Resource.Error("Error: Result is empty")
    }
}
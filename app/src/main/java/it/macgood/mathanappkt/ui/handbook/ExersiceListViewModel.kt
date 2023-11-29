package it.macgood.mathanappkt.ui.handbook

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    val repository: ExerciseRepository,
    val storage: SavedExerciseRepository
) : ViewModel() {

    val range: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val saved: MutableLiveData<List<Exercise>> = MutableLiveData()
    val page: MutableLiveData<Int> = MutableLiveData()


    val _searchExercises: MutableStateFlow<Resource<List<Exercise>>> =
        MutableStateFlow(Resource.Loading())
    val searchExercises: StateFlow<Resource<List<Exercise>>> = _searchExercises.asStateFlow()

    val _exercises: MutableStateFlow<Resource<List<Exercise>>> =
        MutableStateFlow(Resource.Loading())
    val exercises: StateFlow<Resource<List<Exercise>>> = _exercises.asStateFlow()

    val _savedTasks: MutableStateFlow<Resource<List<Exercise>>> =
        MutableStateFlow(Resource.Loading())
    val savedTasks = _savedTasks.asStateFlow()


    init {
        getSavedTasks()
        getExercisesInSearch()
    }

    fun getSavedTasks() {
        viewModelScope.launch {
            storage.getTasks().collect { response ->
                _savedTasks.update {
                    try {
                        Resource.Success(response)
                    } catch (e: Exception) {
                        Resource.Error("Unexpected Error")
                    }
                }
            }
        }
    }

    fun getExercisesInSearch() = viewModelScope.launch {
        val response = repository.getExercises()
        response.collect { list ->
            _searchExercises.update {
                try {
                    Resource.Success(list)
                } catch (e: Exception) {
                    Resource.Success(list)
                }
            }
        }
    }

    fun getExercisesInRange(startId: Int = 0, endId: Int = 0) = viewModelScope.launch {
        val response = repository.getExercises(startId, endId)
        Log.d("TAG", "onCreateView: startId: ${startId}, endId: ${endId}")
        response.collect { list ->
            _exercises.update {
                try {
                    if (startId == 0 && endId == 0) {
                        Resource.Success(list)
                    } else {
                        Resource.Success(list.subList(startId, endId))
                    }

                } catch (e: Exception) {
                    Resource.Success(list)
                }
            }
        }
    }

    fun saveExercise(exercise: ExerciseDto) =
        viewModelScope.launch { storage.insertExercise(exercise.toExercise()) }

    fun deleteExercise(exercise: ExerciseDto) =
        viewModelScope.launch { storage.deleteExercises(exercise.toExercise()) }

}
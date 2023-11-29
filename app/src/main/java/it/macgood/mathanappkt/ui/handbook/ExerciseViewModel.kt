package it.macgood.mathanappkt.ui.handbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.macgood.mathanapp.data.datasource.ExerciseDto
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor() : ViewModel() {

    val exercise: MutableLiveData<ExerciseDto> by lazy {
        MutableLiveData<ExerciseDto>()
    }

}
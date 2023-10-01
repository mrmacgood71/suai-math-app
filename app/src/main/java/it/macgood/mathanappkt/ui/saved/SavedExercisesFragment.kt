package it.macgood.mathanappkt.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanapp.data.datasource.toExerciseDto
import it.macgood.mathanappkt.databinding.FragmentSavedExercisesBinding
import it.macgood.mathanappkt.ui.handbook.demidovich.exercises.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.demidovich.exercises.ExercisesAdapter
import it.macgood.mathanappkt.ui.handbook.demidovich.exercises.exercise.ExerciseViewModel


@AndroidEntryPoint
class SavedExercisesFragment : Fragment() {

    private lateinit var binding: FragmentSavedExercisesBinding

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()
    val exerciseListViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSavedExercisesBinding.inflate(inflater, container, false)

        binding.toolbar.title.text = "Сохранённые задания"
        binding.toolbar.backButton.visibility = View.GONE

        val adapter = ExercisesAdapter(this, exerciseViewModel)
        exerciseListViewModel.getTasks().observe(viewLifecycleOwner) {
            adapter.exercises.submitList(it)
        }

        binding.recyclerView.adapter = adapter

        return binding.root
    }

}
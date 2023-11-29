package it.macgood.mathanappkt.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.core.Resource
import it.macgood.mathanappkt.databinding.FragmentSavedExercisesBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.demidovich.exercises.ExercisesAdapter
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


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

        viewLifecycleOwner.lifecycleScope.launch {
            exerciseListViewModel.savedTasks.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
            .distinctUntilChanged()
            .collect { response ->
                when(response) {
                    is Resource.Error -> { }
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        adapter.exercises.submitList(response.data)
                    }
                }
            }
        }


        binding.recyclerView.adapter = adapter

        return binding.root
    }

}
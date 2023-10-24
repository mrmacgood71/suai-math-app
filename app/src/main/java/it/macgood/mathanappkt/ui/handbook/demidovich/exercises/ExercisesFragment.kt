package it.macgood.mathanappkt.ui.handbook.demidovich.exercises

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.core.Resource
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentExercisesBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExercisesFragment : Fragment() {

    lateinit var binding: FragmentExercisesBinding
    private val viewModel: ExerciseViewModel by activityViewModels()
    private val exerciseListViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val adapter = ExercisesAdapter(this, viewModel)
        val page = arguments?.getInt("page")
        val startId = arguments?.getInt("startId")
        val endId = arguments?.getInt("endId")


        if (page != null && startId != null && endId != null) {
            Log.d("TAG", "onCreateView: startId: ${startId}, endId: ${endId}")
            exerciseListViewModel.getExercisesInRange(startId - 1, endId)
        } else {
            exerciseListViewModel.getExercisesInRange(4, 200)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            exerciseListViewModel.exercises.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
            .distinctUntilChanged()
            .collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            adapter.exercises.submitList(it)
                        }
                        binding.progressBar.visibility = View.GONE
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
                binding.recyclerView.adapter = adapter
            }
        }

        binding = FragmentExercisesBinding.inflate(inflater)

        val range = exerciseListViewModel.range.value?.split(" ")
        val exercise = viewModel.exercise.value

        if (exercise?.questionNumber != null && exercise.questionNumber.isNotEmpty()) {
            binding.recyclerView.scrollToPosition(exercise.questionNumber.toInt() - 1)
        }

        binding.recyclerView.scrollToPosition(exercise?.id?.toInt().let { 0 })

        binding.toolbar.title.text = getString(R.string.list_of_excercises)

        binding.toolbar.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}
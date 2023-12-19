package it.macgood.mathanappkt.ui.handbook.demidovich.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import it.macgood.core.Resource
import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentSearchBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentSearchBinding
    private val exerciseViewModel: ExerciseViewModel by activityViewModels()
    private val listViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        binding.searchText.clearFocus()
        var searchList = mutableListOf<Exercise>()

        val searchAdapter = SearchAdapter(
            onItemClick = {
                val exerciseDto = ExerciseDto(
                    id = it.id,
                    questionText = it.questionText,
                    questionNumber = "",
                    formula = ""
                )
                exerciseViewModel.exercise.postValue(exerciseDto)
            },
            onNavigate = {
                try {
                    val navHostFragment = this.requireActivity()
                        .supportFragmentManager
                        .findFragmentById(R.id.app_placeholder) as NavHostFragment
                    navHostFragment.navController.navigate(R.id.exerciseFragment)

                    this.dismiss()
                } catch (e: NullPointerException) {
                    Log.d("SearchViewHolder", "null")
                }
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            listViewModel.searchExercises.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
            .distinctUntilChanged()
            .collect { response ->
                when (response) {
                    is Resource.Success -> {
                        searchAdapter.differ.submitList(response.data)
                        response.data?.forEach { searchList.add(it) }
                        binding.exercisesProgressBar.visibility = View.GONE
                    }

                    is Resource.Loading -> {
                        binding.exercisesProgressBar.visibility = View.VISIBLE
                        binding.notFoundTextView.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        binding.exercisesProgressBar.visibility = View.GONE
                    }
                }
            }
        }

        binding.recyclerView.adapter = searchAdapter

        binding.searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                val text: String = newText ?: ""
                filter(text, searchAdapter, searchList)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val text: String = query ?: ""
                filter(text, searchAdapter, searchList)
                return true
            }
        })

        binding.buttonCancel.setOnClickListener { dismiss() }

        return binding.root
    }

    fun filter(query: String, adapter: SearchAdapter, list: List<Exercise>) {
        val filteredList: MutableList<Exercise> = mutableListOf()

        list.forEach {
            if (it.id.contains(query)) filteredList.add(it)
            else {
                if (it.questionText != null && query.isNotEmpty()) {
                    if (it.questionText!!.lowercase().contains(query.lowercase())) filteredList.add(
                        it
                    )
                }
            }
        }

        if (filteredList.isEmpty()) {
            binding.notFoundTextView.visibility = View.VISIBLE
        } else {
            binding.notFoundTextView.visibility = View.GONE
        }

        adapter.differ.submitList(filteredList)
    }
}
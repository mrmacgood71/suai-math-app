package it.macgood.mathanappkt.ui.handbook.demidovich

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentChaptersBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.demidovich.search.SearchFragment

@AndroidEntryPoint
class ChaptersFragment : Fragment() {
    private lateinit var binding: FragmentChaptersBinding

    private val exerciseListViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChaptersBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = ChaptersAdapter(Chapters.chapters, exerciseListViewModel)

        binding.toolbar.title.text = "Cборник задач - Б. П. Демидович"

        binding.toolbar.backButton.setOnClickListener {
            findNavController().navigate(R.id.get_handbooks_from_demidovich_chapters) }
        binding.fabSearch.setOnClickListener {
            val searchFragment = SearchFragment()
            searchFragment.show(requireFragmentManager(), "TAG")
        }
        return binding.root
    }
}
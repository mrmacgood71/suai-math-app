package it.macgood.mathanappkt.ui.guidebook.encyclopedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentEncyclopediaBinding

class EncyclopediaFragment : Fragment() {
    lateinit var binding: FragmentEncyclopediaBinding
    val viewModel by activityViewModels<EncyclopediaViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEncyclopediaBinding.inflate(inflater)
        val adapter = EncyclopediaAdapter(
            listOf(
                "A", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П",
                "Р", "С", "Т", "У", "Ф", "Х", "Ч", "Ш", "Э", "Ю",
                "Я"
            ), this)
        binding.recyclerView.adapter = adapter
        val controller = findNavController(requireActivity(), R.id.app_placeholder)
        binding.toolbar.title.text = "Энциклопедия"
        binding.toolbar.backButton.setOnClickListener { controller.navigate(R.id.get_guides_from_encyclopedia) }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }
}
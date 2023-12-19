package it.macgood.mathanappkt.ui.guidebook.author

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentAuthorBinding

class AuthorFragment : Fragment() {

    private lateinit var binding: FragmentAuthorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(inflater, container, false)

        binding.toolbar.title.text = "Авторы проекта"
        binding.toolbar.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}
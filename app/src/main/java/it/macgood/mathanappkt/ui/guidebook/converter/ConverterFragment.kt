package it.macgood.mathanappkt.ui.guidebook.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentConverterBinding
import it.macgood.mathanappkt.ui.MainActivity
import it.macgood.mathanappkt.ui.common.WebClient
import it.macgood.mathanappkt.ui.guidebook.supportingmaterials.MaterialsURL

class ConverterFragment : Fragment() {
    lateinit var binding: FragmentConverterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentConverterBinding.inflate(inflater)

        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(MaterialsURL.CONVERTER)
            webView.webViewClient =
                WebClient()

            toolbar.title.text = "Конвертер"

            toolbar.backButton.setOnClickListener{
                val navController
                        = Navigation.findNavController(
                    inflater.context as MainActivity,
                    R.id.app_placeholder
                )
                navController.navigate(R.id.get_guidebook_from_converter)
            }
        }

        return binding.root
    }
}
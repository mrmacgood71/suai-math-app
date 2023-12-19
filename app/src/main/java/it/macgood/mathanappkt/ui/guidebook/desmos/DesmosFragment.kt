package it.macgood.mathanappkt.ui.guidebook.desmos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentDesmosBinding
import it.macgood.mathanappkt.ui.MainActivity
import it.macgood.mathanappkt.ui.common.WebClient

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DesmosFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentDesmosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDesmosBinding.inflate(inflater)

        binding.toolbar.title.text = "Desmos"

        binding.toolbar.backButton.setOnClickListener{
            val navController
                    = findNavController(
                inflater.context as MainActivity,
                R.id.app_placeholder
            )
            navController.popBackStack()

        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.desmos.com/calculator?lang=ru")
        binding.webView.webViewClient =
            WebClient()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DesmosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
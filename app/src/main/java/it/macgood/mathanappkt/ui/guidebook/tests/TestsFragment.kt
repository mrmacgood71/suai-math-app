package it.macgood.mathanappkt.ui.guidebook.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentTestsBinding
import it.macgood.mathanappkt.ui.MainActivity
import it.macgood.mathanappkt.ui.common.WebClient

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val PLACEHOLDER_TEST = "https://docs.google.com/forms/d/e/1FAIpQLScJPjmo884XhXhT3BlBf5BEOEawZArVNptIJCUiv9vMpLiGXA/viewform?usp=sf_link"

class TestsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentTestsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestsBinding.inflate(inflater)

        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(PLACEHOLDER_TEST)
            webView.webViewClient = WebClient()

            toolbar.title.text = "Тесты"


            toolbar.backButton.setOnClickListener{
                val navController
                        = Navigation.findNavController(
                    inflater.context as MainActivity,
                    R.id.app_placeholder
                )
                navController.navigate(R.id.get_guidebook_from_tests)
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package it.macgood.mathanappkt.ui.guidebook.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentDownloadBinding
import it.macgood.mathanappkt.ui.MainActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@Deprecated("Using SupportingMaterials", replaceWith = ReplaceWith("SupportingMaterialsFragment.kt"))
class DownloadFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentDownloadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDownloadBinding.inflate(inflater)

        binding.toolbar.title.text = "Загрузки"

        binding.toolbar.backButton.setOnClickListener{
            val navController
                    = Navigation.findNavController(
                inflater.context as MainActivity,
                R.id.app_placeholder
            )

            navController.popBackStack()

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DownloadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
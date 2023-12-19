package it.macgood.mathanappkt.ui.guidebook.remark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.common.navigate
import it.macgood.mathanappkt.databinding.FragmentRemarkBinding
import it.macgood.mathanappkt.ui.MainActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RemarkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentRemarkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemarkBinding.inflate(inflater)

        binding.remark.text = "" +
                "Тестирование проводится в сервисе Google forms - https://www.google.com/forms/about/" +
                "Изображение графиков проводится в сервисе Desmos - https://www.desmos.com/?lang=ru" +
                "Ковертация изображений проводится в сервисе ILovePdf - ilovepdf.com/"

        binding.toolbar.title.text = "Примечания"

        binding.toolbar.backButton.setOnClickListener {
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
            RemarkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
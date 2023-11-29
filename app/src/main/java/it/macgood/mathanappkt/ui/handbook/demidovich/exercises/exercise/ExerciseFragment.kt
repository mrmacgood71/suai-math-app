package it.macgood.mathanappkt.ui.handbook.demidovich.exercises.exercise

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentExerciseBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.scilab.forge.jlatexmath.ParseException
import javax.inject.Inject

private const val ID = "param1"
private const val SIZE = "param2"

@AndroidEntryPoint
class ExerciseFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var repository: ExerciseRepository

    private val viewModel: ExerciseViewModel by activityViewModels()
    private val listViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ID)
            param2 = it.getString(SIZE)
        }
    }

    lateinit var binding: FragmentExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExerciseBinding.inflate(inflater)

        with(binding) {

            var formula = ""
            var id = "0"

            viewModel.exercise.observe(viewLifecycleOwner) {
                id = it.id
                toolbar.title.text = "Задача № $id"

                CoroutineScope(Dispatchers.Main).launch {
                    val repositoryFormula = repository.getExercise(it.id)
                    val fix = "\$${repositoryFormula.formula}\$"
                    if (repositoryFormula.formula == null || repositoryFormula.formula!!.isEmpty()) {
                        jlmvView.setLatex("Задание\\ без\\ формулы")
                    } else {
                        try {
                            jlmvView.setLatex(fix)
                        } catch (e: ParseException) {
                            jlmvView.setLatex("Формула")
                        }

                        formula = repositoryFormula.formula!!
                    }
                    text.text = repositoryFormula.questionText
                }
            }

            copyLaTeXButton.setOnClickListener {
                val clipboard: ClipboardManager? =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", formula)
                clipboard?.setPrimaryClip(clip)
                Snackbar.make(
                    requireView(),
                    "Формула скопирована в буфер обмена",
                    Snackbar.LENGTH_LONG
                )
                    .setAnchorView(R.id.bottom_navigation_bar)
                    .show()
            }

            saveExerciseButton.setOnClickListener {
                listViewModel.saveExercise(viewModel.exercise.value!!)
                Snackbar.make(requireView(), "Задача сохранена!", Snackbar.LENGTH_SHORT)
                    .setAnchorView(R.id.bottom_navigation_bar)
                    .show()
            }

            toolbar.backButton.setOnClickListener { view ->
                view.findNavController().popBackStack()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ID, param1)
                    putString(SIZE, param2)
                }
            }
    }
}
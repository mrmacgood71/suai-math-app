package it.macgood.mathanappkt.ui.saved

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanappkt.databinding.FragmentExerciseActionsBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel

@AndroidEntryPoint
class ExerciseActionsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentExerciseActionsBinding

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()
    private val exerciseListViewModel: ExerciseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseActionsBinding.inflate(inflater, container, false)

        exerciseViewModel.exercise.observe(viewLifecycleOwner) { exercise ->
            binding.exerciseLegendTextView.text = "Действия с задачаей №${exercise.id}: ${exercise.questionText}"

            binding.deleteButtonLayout.setOnClickListener {
                exerciseListViewModel.deleteExercise(exercise)
                this.dismiss()
            }

            binding.shareButtonLayout.setOnClickListener {
                requireContext().shareLink("https://mathapp.com/demidovich?task=${exercise.id}")
            }
        }

        binding.buttonCancel.setOnClickListener {
            this.dismiss()
        }

        return binding.root
    }

    fun Context.shareLink(url: String) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}
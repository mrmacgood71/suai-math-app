package it.macgood.mathanappkt.ui.handbook.demidovich.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.ItemExerciseBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel
import it.macgood.mathanappkt.ui.saved.ExerciseActionsFragment

class ExercisesAdapter(
    private val parent: Fragment,
    private val viewModel: ExerciseViewModel
) : RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Exercise>() {

        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }

    val exercises = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemExerciseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises.currentList[position]
        with(holder) {

            binding.number.text = "Задача № ${exercise.id}"
            binding.text.text = exercise.questionText

            if (parent is ExercisesFragment) {
                configListExerciseItem(exercise)
            } else {
                configSavedExerciseItem(exercise)
            }
        }
    }

    private fun ViewHolder.configListExerciseItem(exercise: Exercise) {
        itemView.setOnClickListener {
            viewModel.exercise.postValue(
                ExerciseDto(
                    id = exercise.id,
                    questionNumber = "",
                    questionText = exercise.questionText,
                    formula = exercise.formula
                )
            )
            it.findNavController().navigate(R.id.action_exercisesFragment_to_exerciseFragment, bundleOf("fromSaved" to false))

        }
    }

    private fun ViewHolder.configSavedExerciseItem(exercise: Exercise) {
        itemView.setOnClickListener {
            viewModel.exercise.postValue(
                ExerciseDto(
                    id = exercise.id,
                    questionNumber = "",
                    questionText = exercise.questionText,
                    formula = exercise.formula
                )
            )
            it.findNavController()
                .navigate(R.id.action_savedExercisesFragment_to_exerciseFragment, bundleOf("fromSaved" to true))

        }
        itemView.setOnLongClickListener {

            viewModel.exercise.postValue(
                ExerciseDto(
                    id = exercise.id,
                    questionNumber = "",
                    questionText = exercise.questionText,
                    formula = exercise.formula
                )
            )

            val actionsFragment = ExerciseActionsFragment()
            actionsFragment.show(parent.parentFragmentManager, "tag")
            
            true
        }
    }

    override fun getItemCount(): Int {
        return exercises.currentList.size
    }

    inner class ViewHolder(val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root)
}
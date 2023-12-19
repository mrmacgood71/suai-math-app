package it.macgood.mathanappkt.ui.handbook.demidovich

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.ItemChaptersBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import java.util.regex.Pattern

class ChaptersAdapter(
    private val chapters: List<Chapter>,
    val viewModel: ExerciseListViewModel
) : RecyclerView.Adapter<ChaptersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChaptersBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chapter = chapters[position]
        with(holder) {
            binding.item.setOnClickListener {
                val range = range
                viewModel.range.value = range
                viewModel.page.postValue(chapter.id)
                it.findNavController().navigate(R.id.action_chaptersFragment_to_exercisesFragment, bundleOf(
                    "startId" to chapter.range.first,
                    "endId" to chapter.range.last,
                    "page" to chapter.id)
                )
            }
            binding.textChapter.text = chapter.chapter
            binding.textName.text = chapter.name
            binding.textExercises.text = chapter.exercises
            binding.textPages.text = chapter.pages
        }
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    inner class ViewHolder(
        val binding: ItemChaptersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val range: String
            get() {
                val matcher = Pattern.compile("\\d+")
                    .matcher(binding.textExercises.text)
                var range = ""
                while (matcher.find()) {
                    range += matcher.group() + " "
                }
                return range
            }
    }
}
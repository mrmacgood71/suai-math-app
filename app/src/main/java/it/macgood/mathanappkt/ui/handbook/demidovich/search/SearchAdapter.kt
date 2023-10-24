package it.macgood.mathanappkt.ui.handbook.demidovich.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanappkt.databinding.ItemSearchBinding


class SearchAdapter(
    val onItemClick: (Exercise) -> Unit,
    val onNavigate: () -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Exercise>() {

        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return SearchViewHolder(ItemSearchBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.textQuestion.text = item.questionText
        holder.binding.number.text = "№ ${item.id}"

        holder.itemView.setOnClickListener {
            onItemClick(item)
            onNavigate()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setFilteredList(list: List<Exercise>) {
        differ.submitList(list)
    }

    inner class SearchViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
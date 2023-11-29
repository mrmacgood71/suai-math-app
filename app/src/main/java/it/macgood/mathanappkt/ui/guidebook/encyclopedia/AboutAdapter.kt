package it.macgood.mathanappkt.ui.guidebook.encyclopedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanappkt.databinding.ItemEncyclopediaAboutBinding

class AboutAdapter : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<Term>() {

        override fun areItemsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemEncyclopediaAboutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alphabet = differ.currentList[position]
        holder.binding.termTextView.text = alphabet.name
        holder.binding.descTextView.text = alphabet.desc
    }

    override fun getItemCount(): Int = differ.currentList.size

    class ViewHolder(val binding: ItemEncyclopediaAboutBinding) : RecyclerView.ViewHolder(binding.root)
}
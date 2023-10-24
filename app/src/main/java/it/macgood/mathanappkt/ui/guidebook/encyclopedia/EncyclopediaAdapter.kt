package it.macgood.mathanappkt.ui.guidebook.encyclopedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.ItemAlphabetBinding

class EncyclopediaAdapter(
    private val mAlphabets: List<String>?,
    private val parent: EncyclopediaFragment
) : RecyclerView.Adapter<EncyclopediaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemAlphabetBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.letter.text = mAlphabets!![position]
        holder.binding.alphabetDescription.text = "Например: ${EncyclopediaData.termsList[position].name}"
        holder.itemView.setOnClickListener { v: View? ->
            parent.viewModel.letter.postValue(
                mAlphabets[position]
            )
            v?.findNavController()?.navigate(R.id.action_encyclopediaFragment_to_aboutFragment)
        }
    }

    override fun getItemCount(): Int {
        return mAlphabets?.size ?: 0
    }

    inner class ViewHolder(val binding: ItemAlphabetBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
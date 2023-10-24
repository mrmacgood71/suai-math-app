package it.macgood.mathanappkt.ui.guidebook.supportingmaterials

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.common.Constants
import it.macgood.mathanappkt.databinding.ItemSuppotringMaterialBinding


class SupportingMaterialsAdapter(
    val materials: List<Material>
) : RecyclerView.Adapter<SupportingMaterialsAdapter.MaterialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        return MaterialViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_suppotring_material, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.bind(materials[position])
    }

    override fun getItemCount(): Int {
        return materials.size
    }

    class MaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSuppotringMaterialBinding.bind(itemView)
        fun bind(material: Material) = with(binding) {

            binding.textAuthor.text = material.author
            binding.textName.text = material.name

            binding.material.setOnClickListener {
                downloadImage(
                    url = Constants.DOWNLOAD_URL + material.url,
                    outputFileName = material.name + ".pdf",
                    context = itemView.context
                )
                Toast.makeText(itemView.context, "Загрузка началась", Toast.LENGTH_SHORT).show()
            }
        }

        private fun downloadImage(url: String?, outputFileName: String?, context: Context) {
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle(outputFileName)
            request.setDescription("Downloading $outputFileName")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.allowScanningByMediaScanner()
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                outputFileName
            )
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            manager!!.enqueue(request)
        }
    }
}
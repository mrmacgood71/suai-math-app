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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


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

//                try {
//                    when(material.id) {
//                        1L -> {}
//                        2L -> {}
//                        3L -> {}
//                        4L -> {}
//                        5L -> {}
//                    }
//                    val `in`: InputStream = it.context.getResources().openRawResource(R.raw.fiht)
//
//                    val downloadsDir =
//                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                    val outputFile = File(downloadsDir, "fihtengholtz.pdf")
//
//                    val out: OutputStream = FileOutputStream(outputFile)
//
//                    val buffer = ByteArray(1024)
//                    var length: Int
//                    while (`in`.read(buffer).also { length = it } > 0) {
//                        out.write(buffer, 0, length)
//                    }
//
//                    `in`.close()
//                    out.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }

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
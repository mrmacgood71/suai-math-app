package it.macgood.mathanappkt.ui.guidebook.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import it.macgood.mathanappkt.R
import it.macgood.mathanappkt.databinding.FragmentContactsBinding


class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)

        binding.toolbar.title.text = "Контакты"
        binding.toolbar.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_navigation_guidebook)
        }

        binding.telegramLayout.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/macgoodmonsta"))
            startActivity(intent)
        }

        binding.githubAndroidLayout.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mrmacgood71/mathan-app"))
            startActivity(intent)
        }

        binding.gitBackendLayout.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/PaveqP/math-analysis-dev-server"))
            startActivity(intent)
        }

        return binding.root
    }

}
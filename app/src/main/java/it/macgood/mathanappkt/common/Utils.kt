package it.macgood.mathanappkt.common

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

inline fun Fragment.navigate(action: Int) {
    try {
        val activity = requireActivity() as Activity
        var parent = requireView().parent
        while (parent !is FragmentContainerView) {
            parent = parent.parent
        }
        val navController = activity.findNavController(parent.id)
        navController.navigate(action)
    } catch (e: Exception) {
        error("Navigation Error")
    }
}

inline fun RecyclerView.ViewHolder.navigate(action: Int) {
    try {
        val activity = itemView.context as Activity
        var parent = itemView.parent
        while (parent !is FragmentContainerView) {
            parent = parent.parent
        }
        val navController = activity.findNavController(parent.id)
        navController.navigate(action)
    } catch (e: java.lang.Exception) {
        error("Navigation Error")
    }
}


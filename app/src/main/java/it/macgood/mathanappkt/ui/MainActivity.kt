package it.macgood.mathanappkt.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanappkt.R
import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import it.macgood.mathanappkt.databinding.ActivityMainBinding
import it.macgood.mathanappkt.ui.handbook.ExerciseListViewModel
import it.macgood.mathanappkt.ui.handbook.ExerciseViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private lateinit var viewModel: ExerciseViewModel
//    private lateinit var listViewModel: ExerciseListViewModel

    private val exerciseListViewModel: ExerciseListViewModel by viewModels()
    private val viewModel: ExerciseViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val bottomBar = binding.bottomNavigationBar

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_guidebook, R.id.navigation_handbook, R.id.savedExercisesFragment
        ))

        val navController = findNavController(this, R.id.app_placeholder)
        setupWithNavController(bottomBar, navController)

        val intent = intent
        if (intent.data != null) {
            val query = intent.data!!.query?.substring(5)
            viewModel.exercise.value = ExerciseDto(
                query ?: "",
                query ?: "",
                "",
                "")

            navController.navigate(R.id.action_navigation_guidebook_to_exerciseFragment)
        }
    }
}
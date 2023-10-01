package it.macgood.mathanappkt.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import it.macgood.mathanappkt.R
import it.macgood.mathanapp.data.datasource.ExerciseDto
import it.macgood.mathanappkt.databinding.ActivityMainBinding
import it.macgood.mathanappkt.ui.handbook.demidovich.exercises.exercise.ExerciseViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ExerciseViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
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
            Log.d("TAG", "onCreate: $query")
        }
    }
}
package it.macgood.mathanappkt.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.macgood.mathanapp.data.datasource.MathAnalysisCallback
import it.macgood.mathanapp.data.datasource.MathAnalysisDatabase
import it.macgood.mathanapp.data.datasource.MathAnalysisDatabase.Companion.DATABASE_NAME
import it.macgood.mathanapp.data.datasource.TaskDao
import it.macgood.mathanapp.data.repository.ExerciseRepositoryImpl
import it.macgood.mathanapp.data.repository.SavedExerciseRepositoryImpl
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import it.macgood.mathanapp.domain.repository.SavedExerciseRepository
import it.macgood.mathanapp.domain.usecase.GetTasks
import it.macgood.mathanapp.domain.usecase.TasksUseCases
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMathAnalysisDatabase(
        app: Application,
        provider: Provider<TaskDao>
    ): MathAnalysisDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            MathAnalysisDatabase::class.java,
            DATABASE_NAME
        )
        .addCallback(MathAnalysisCallback(app.applicationContext, provider))
        .build()
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(): MathAnalysisApi {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MathAnalysisApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        dao: TaskDao
    ): ExerciseRepository {
        return ExerciseRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: MathAnalysisDatabase): SavedExerciseRepository {
        return SavedExerciseRepositoryImpl(database.taskDao)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: ApplicationContext) = context

    @Provides
    @Singleton
    fun provideTaskDao(db: MathAnalysisDatabase) = db.taskDao

    @Provides
    @Singleton
    fun providesUseCases(repository: SavedExerciseRepository): TasksUseCases {
        return TasksUseCases(getTasks = GetTasks(repository))
    }
}
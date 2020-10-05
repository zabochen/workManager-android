package ua.ck.zabochen.android.workmanager.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ua.ck.zabochen.android.workmanager.data.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) = AppDatabase(application)

    @Provides
    @Singleton
    fun provideAudioFilesDao(appDatabase: AppDatabase) = appDatabase.audioFileDao()
}
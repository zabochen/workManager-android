package ua.ck.zabochen.android.workmanager.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ua.ck.zabochen.android.workmanager.data.preferences.PreferencesService
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesService(application: Application) = PreferencesService(application)
}
package ua.ck.zabochen.android.workmanager.data.preferences

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore

abstract class PreferencesProvider(application: Application) {
    val preferences: DataStore<Preferences> =
        application.createDataStore("preferences")
}
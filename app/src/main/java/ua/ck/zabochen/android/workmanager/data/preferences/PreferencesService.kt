package ua.ck.zabochen.android.workmanager.data.preferences

import android.app.Application
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesService @Inject constructor(
    application: Application
) : PreferencesProvider(application) {

    val getWorkerUUID: Flow<String> = preferences.data.map { it[WORKER_UUID] ?: "" }

    suspend fun setWorkerUUID(workerUUID: String) {
        preferences.edit { preferences ->
            preferences[WORKER_UUID] = workerUUID
        }
    }

    companion object {
        val WORKER_UUID = preferencesKey<String>("worker_uuid")
    }
}
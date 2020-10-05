package ua.ck.zabochen.android.workmanager.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.ck.zabochen.android.workmanager.data.preferences.PreferencesService
import ua.ck.zabochen.android.workmanager.model.database.AudioFile
import ua.ck.zabochen.android.workmanager.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val preferencesService: PreferencesService,
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getWorkerUUID() = preferencesService.getWorkerUUID

    fun saveWorkerUUID(uuid: String) = viewModelScope.launch {
        preferencesService.setWorkerUUID(uuid)
    }

    fun clearWorkerUUID() = viewModelScope.launch {
        preferencesService.setWorkerUUID("")
    }

    fun getAllAudioFile() = mainRepository.getAllAudioFiles()
    fun insertAudioFile(audioFile: AudioFile) = mainRepository.insertAudioFile(audioFile)
}
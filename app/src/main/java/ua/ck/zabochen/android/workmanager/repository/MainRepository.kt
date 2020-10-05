package ua.ck.zabochen.android.workmanager.repository

import ua.ck.zabochen.android.workmanager.model.database.AudioFile
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val audioFilesDao: AudioFile.AudioFilesDao
) {
    fun insertAudioFile(audioFile: AudioFile) = audioFilesDao.insertAudioFile(audioFile)
    fun getAllAudioFiles() = audioFilesDao.getAllAudioFile()
}
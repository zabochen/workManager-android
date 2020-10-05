package ua.ck.zabochen.android.workmanager.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ua.ck.zabochen.android.workmanager.model.database.AudioFile.Companion.AUDIO_FILES_TABLE_NAME

@Entity(tableName = AUDIO_FILES_TABLE_NAME)
data class AudioFile(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val localPath: String = ""
) {
    @Dao
    interface AudioFilesDao {
        @Query(value = "SELECT * FROM audio_files")
        fun getAllAudioFile(): LiveData<List<AudioFile>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAudioFile(audioFile: AudioFile): Long
    }

    companion object {
        const val AUDIO_FILES_TABLE_NAME = "audio_files"
    }
}
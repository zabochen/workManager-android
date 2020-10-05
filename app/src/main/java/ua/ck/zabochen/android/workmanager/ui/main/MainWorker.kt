package ua.ck.zabochen.android.workmanager.ui.main

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ua.ck.zabochen.android.workmanager.data.database.AppDatabase
import ua.ck.zabochen.android.workmanager.model.database.AudioFile
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        // Get input data
        val fileUrl = inputData.getString(KEY_FILE_URL) ?: ""
        Log.i("MainWorker", "doWork: FILE_URL = $fileUrl")

        // Delay operations
        for (i in 1..10) {
            TimeUnit.SECONDS.sleep(1)
            Log.i("MainWorker", "doWork: $i")
        }

        // Generate random id & add to database
        val audioFileRandomId = Random.nextInt(0, 10000)
        val audioFileId: Long = try {
            AppDatabase.invoke(application = context).audioFileDao().insertAudioFile(
                AudioFile(audioFileRandomId, "file_path: $audioFileRandomId")
            )
        } catch (e: Exception) {
            -1L
        }

        Log.i("MainWorker", "doWork: insert result: $audioFileId")
        return Result.success()
    }

    companion object {
        const val KEY_FILE_URL = "key_file_url"
    }
}
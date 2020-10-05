package ua.ck.zabochen.android.workmanager

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApp : Application() {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this@MainApp)
    }
}


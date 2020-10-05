package ua.ck.zabochen.android.workmanager.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import ua.ck.zabochen.android.workmanager.databinding.ActivityMainBinding
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initUi()
    }

    private fun initBinding() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initUi() {
        initRecyclerView()
        initStartButton()
        initWorkerStatusById()
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        val audioFileAdapter = AudioFileAdapter()

        // RecyclerView: config
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = audioFileAdapter

        // Adapter: update
        mainViewModel.getAllAudioFile().observe(this@MainActivity) { audioFiles ->
            audioFileAdapter.submitList(audioFiles)
        }
    }

    private fun initStartButton() = with(binding.btnStartWork) {
        setOnClickListener { initWorker() }
    }

    // https://stackoverflow.com/questions/54368306/update-database-entry-in-room-using-workmanager
    private fun initWorker() = with(WorkManager.getInstance(this)) {

        // Input Data
        val inputWorkerData = Data.Builder()
            .putString(MainWorker.KEY_FILE_URL, "http://file.url.com")
            .build()

        // Request
        val mainWorker = OneTimeWorkRequestBuilder<MainWorker>()
            .setInputData(inputWorkerData)
            .build()

        // Save worker ID
        val workerId = mainWorker.id
        mainViewModel.saveWorkerUUID(workerId.toString())

        // Start worker
        // We can start UniqueWork with difference ExistingWorkPolicy:
        // .beginUniqueWork("work123", ExistingWorkPolicy.REPLACE, myWorkRequest1)
        enqueue(mainWorker)

        // Show worker state
        initWorkerStatusById()
    }

    private fun initWorkerStatusById() = with(mainViewModel) {
        getWorkerUUID().asLiveData().observe(this@MainActivity) {
            if (it.isNotEmpty()) {
                WorkManager.getInstance(this@MainActivity)
                    .getWorkInfoByIdLiveData(UUID.fromString(it)).observe(this@MainActivity) { workInfo ->
                        showWorkerResult(workInfo)
                    }
            }
        }
    }

    private fun showWorkerResult(workInfo: WorkInfo) = with(tvWorkResult) { this.text = workInfo.state.toString() }
}
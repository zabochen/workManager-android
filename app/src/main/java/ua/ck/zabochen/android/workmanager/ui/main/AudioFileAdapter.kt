package ua.ck.zabochen.android.workmanager.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.ck.zabochen.android.workmanager.databinding.AdapterItemAudioFileBinding
import ua.ck.zabochen.android.workmanager.model.database.AudioFile

class AudioFileAdapter : ListAdapter<AudioFile, AudioFileAdapter.AudioFileViewHolder>(AudioFileComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AudioFileViewHolder(
            AdapterItemAudioFileBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AudioFileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AudioFileViewHolder(
        private val binding: AdapterItemAudioFileBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(audioFile: AudioFile) = with(binding) {
            tvAudioFileId.text = audioFile.id.toString()
            tvAudioFileLocalPath.text = audioFile.localPath
        }
    }

    private object AudioFileComparator : DiffUtil.ItemCallback<AudioFile>() {
        override fun areItemsTheSame(oldItem: AudioFile, newItem: AudioFile) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AudioFile, newItem: AudioFile) =
            oldItem == newItem
    }
}


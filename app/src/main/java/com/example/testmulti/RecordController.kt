package com.example.testmulti

import android.content.Context
import android.media.MediaRecorder
import timber.log.Timber
import java.io.File

class RecordController(private val context: Context) {
    private var audioRecorder: MediaRecorder? = null
    fun start() {
        Timber.d("Start")
        audioRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(getAudioPath())
            prepare()
            start()
        }
    }

    private fun getAudioPath(): String {
        return "${context.cacheDir.absolutePath}${File.pathSeparator}${System.currentTimeMillis()}.wav"
    }

    fun stop() {
        audioRecorder?.let {
            Timber.d("Stop")
            it.stop()
            it.release()
        }
        audioRecorder = null
    }

    fun isAudioRecording() = audioRecorder != null
    fun getVolume() = audioRecorder?.maxAmplitude ?: 0
}
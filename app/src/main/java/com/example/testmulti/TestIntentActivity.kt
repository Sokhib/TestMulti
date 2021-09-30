package com.example.testmulti

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TestIntentActivity() : AppCompatActivity() {
    var one = 1
    val viewModel: TestIntentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent)
        val textView = findViewById<TextView>(R.id.zibabText)

        textView.setOnClickListener {
            viewModel.increase()
            textView.text = (++one).toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.d("One :${one}")
        Timber.d("Two :${viewModel.print()}")

        Timber.d("onStart")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("One :${one}")
        Timber.d("Two :${viewModel.print()}")

        Timber.d("onStop")

    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("One :${one}")
        Timber.d("Two :${viewModel.print()}")

        Timber.d("onRestart")

    }

    override fun onResume() {
        super.onResume()
        Timber.d("One :${one}")
        Timber.d("Two :${viewModel.print()}")

        Timber.d("onResume")

    }

    override fun onPause() {
        super.onPause()
        Timber.d("One :${one}")
        Timber.d("Two :${viewModel.print()}")
        Timber.d("onPause")

    }
}
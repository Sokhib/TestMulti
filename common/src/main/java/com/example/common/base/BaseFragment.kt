package com.example.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.common.extension.observe
import com.example.common.helper.Event
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseFragment<BVM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes val layout: Int
) : Fragment() {
    var binding: DB? = null

    abstract fun getViewModel(): BVM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layout, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModel = getViewModel()

        viewModel.progressState.observe(viewLifecycleOwner, {
            if (it != 0) {
                Timber.d("Show Progress")
            } else {
                Timber.d("Hide Progress")
            }

        })
        lifecycleScope.launch {
            observe(viewModel.messageText, ::showMessage)
        }
        return binding!!.root
    }

    private fun showMessage(content: Event<String>) {
        content.getContentIfNotHandled()?.let { message ->
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
//        progressBar!!.dismiss()
//        progressBar = null
        binding = null
    }

}
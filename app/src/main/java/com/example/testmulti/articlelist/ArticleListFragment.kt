package com.example.testmulti.articlelist

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.example.common.base.BaseFragment
import com.example.common.extension.observe
import com.example.testmulti.R
import com.example.testmulti.databinding.ArticleListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleListFragment :
    BaseFragment<ArticleListViewModel, ArticleListFragmentBinding>(R.layout.article_list_fragment) {

    private val articleVM: ArticleListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewmodel = articleVM
        observe(articleVM.state, ::setCircleState)

        binding!!.circleView.setOnClickListener {
            //List of apps handling SEND Action
//            val pm = requireActivity().packageManager
//            val mainIntent = Intent(Intent.ACTION_SEND, null)
//            mainIntent.putExtra("Zibab", "Ivj")
//            mainIntent.type = "text/plain"
//            val resolveInfos = pm.queryIntentActivities(
//                mainIntent,
//                0
//            )
//            for (info in resolveInfos) {
//                val applicationInfo = info.activityInfo.applicationInfo
//                Timber.d("${applicationInfo.loadLabel(pm)}")
//                Timber.d(applicationInfo.packageName)
//            }


            //Night Mode Check
            val uiModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            //Change mode with Act-recreation
            if (uiModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


            //Clipboard set
//            val clipboard: ClipboardManager? =
//                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
//            val clip = ClipData.newPlainText("label", "text")
//            clipboard?.setPrimaryClip(clip)

        }

    }


    override fun getViewModel() = articleVM
    private fun setCircleState(state: Boolean) {
        if (state) binding!!.circleView.setChecked()
    }
}


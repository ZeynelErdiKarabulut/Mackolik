package com.zeynelerdi.mackolik.ui.browser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.*
import androidx.databinding.DataBindingUtil
import com.zeynelerdi.mackolik.R
import com.zeynelerdi.mackolik.databinding.ActivityBrowserBinding
import com.zeynelerdi.mackolik.ui.common.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_browser.*
import javax.inject.Inject


class BrowserActivity : BaseActivity(), BrowserNavigator {

    private lateinit var browserActivityDataBinding: ActivityBrowserBinding

    @Inject
    lateinit var browserViewModel: BrowserViewModel

    companion object ARG{
        const val ARG_URL = "ARG_URL"
    }

    override fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup {
        browserActivityDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_browser)
        return rootBrowserActivity as ViewGroup
    }

    override fun bindView() {
        setWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.builtInZoomControls = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String,
                callback: GeolocationPermissions.Callback
            ) {
                callback.invoke(origin, true, false)
            }

            override fun onProgressChanged(view: WebView, progress: Int) {}
        }
        webView.loadUrl(intent.getStringExtra(ARG_URL))
    }
}

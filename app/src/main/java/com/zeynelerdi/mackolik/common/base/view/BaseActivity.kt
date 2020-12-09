package com.zeynelerdi.mackolik.ui.common.base.view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import com.zeynelerdi.mackolik.ui.common.base.BaseNavigator
import com.zeynelerdi.mackolik.ui.common.components.progressdialog.CustomProgressDialog
import com.zeynelerdi.mackolik.util.extension.isNull
import com.zeynelerdi.mackolik.util.extension.notNull
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseNavigator {

    private var progressDialog: CustomProgressDialog? = null
    open var rootView: ViewGroup? = null

    abstract fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup

    abstract fun bindView()


    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)

        rootView = onCreateActivity(savedInstanceState)
        bindView()
    }

    private fun hideProgress() {
        progressDialog.notNull { it.cancel() }
    }

    private fun showProgress() {
        hideProgress()
        progressDialog.isNull {
            progressDialog = CustomProgressDialog(this)
        }
        progressDialog?.show()
    }

    override fun hideLoading() {
        hideProgress()
    }

    override fun showLoading() {
        showProgress()
    }

    override fun showFail(message: String, goBack: Boolean) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        if (goBack) onBackPressed()
    }

    private fun performDI() = AndroidInjection.inject(this)
}
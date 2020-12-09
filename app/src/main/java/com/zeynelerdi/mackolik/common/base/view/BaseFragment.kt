package com.zeynelerdi.mackolik.ui.common.base.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zeynelerdi.mackolik.ui.common.base.BaseNavigator
import com.zeynelerdi.mackolik.ui.common.components.progressdialog.CustomProgressDialog
import com.zeynelerdi.mackolik.util.extension.isNull
import com.zeynelerdi.mackolik.util.extension.notNull
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(), BaseNavigator {

    private var progressDialog: CustomProgressDialog? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun hideLoading() {
        progressDialog.notNull { it.cancel() }
    }

    override fun showFail(message: String, goBack: Boolean) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        if (goBack) activity?.onBackPressed()
    }

    override fun showLoading() {
        hideLoading()
        this.context.notNull { cxt ->
            progressDialog.isNull {
                progressDialog = CustomProgressDialog(cxt)
            }
            progressDialog?.show()
        }
    }

    private fun performDI() = AndroidSupportInjection.inject(this)
}
package com.zeynelerdi.mackolik.ui.common.base.view

abstract class BaseFragmentActivity : BaseActivity() {

    protected abstract var frameLayoutId: Int

    fun initView(fragment: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frameLayoutId, fragment).commitAllowingStateLoss()

    }

    fun clearStack() {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun changeFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().replace(frameLayoutId, fragment).commit()
    }
}

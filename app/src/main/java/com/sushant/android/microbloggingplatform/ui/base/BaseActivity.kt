package com.sushant.android.microbloggingplatform.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sushant.android.microbloggingplatform.model.service.ApiService
import com.sushant.android.microbloggingplatform.util.Utils
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding>(private val mViewModelClass: Class<VM>): DaggerAppCompatActivity(){


    var author_id:Int = -1

    val serviceClient by lazy {
        ApiService.create()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as VDB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(mViewModelClass)
    }

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
        onInject()
        setupBindingLifecycleOwner()
    }

    abstract fun initViewModel(viewModel: VM)

    private fun setupBindingLifecycleOwner() {
        binding.lifecycleOwner = this
    }

    var progressDialog: ProgressDialog? = null

    fun showMessage(message: String) {
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_LONG).show()
    }

    fun showLoading() {
        progressDialog = Utils.showLoadingDialog(this@BaseActivity)
    }

    fun hideLoading() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()

            }
        }
    }

}
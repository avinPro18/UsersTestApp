package com.example.userstestapplication.base

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefs
import kotlinx.coroutines.launch
import com.example.userstestapplication.model.Result
import com.example.userstestapplication.utils.logE
import com.example.userstestapplication.utils.showToastMsg
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<BindingLayout: ViewBinding, ViewModelType: ViewModel>: AppCompatActivity() {

    lateinit var binding: BindingLayout
    var viewModel: ViewModelType? = null
    var loader: View? = null
    var toolbarCountView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingLayout()
        setContentView(binding.root)
        viewModel = getViewModelClass()
        loader = setLoaderView()
        toolbarCountView = setToolbarCountView()
        init()
    }

    abstract fun init()
    abstract fun getBindingLayout(): BindingLayout
    abstract fun getViewModelClass(): ViewModelType

    /**
     * return null if no loader reqd.
     */
    open fun setLoaderView(): View? {
        return null
    }

    open fun setToolbarCountView(): TextView? {
        return null
    }

    open fun updateAppOpenedCount(count: Int) {
        checkIfViewIsSet(toolbarCountView){
            toolbarCountView?.text = "App Opened Count: $count"
        }
    }

    /**
     * These functions can only be used if loader view is set
     */
    open fun showLoader() = checkIfViewIsSet(loader) { loader?.let { it.visibility = View.VISIBLE } }
    open fun hideLoader() = checkIfViewIsSet(loader) { loader?.let { it.visibility = View.GONE } }

    private fun checkIfViewIsSet(view: View?, proceed: () -> Unit){
        if(view != null)
            proceed()
        else
            logE("View not set in activity")
    }

    open fun <T: Parcelable> goToActivity(activity: Activity, data: T? = null){
        val intent = Intent(this, activity::class.java)
        if(data != null)
            intent.putExtra("data", data)
        startActivity(intent)
    }

    open fun goToActivity(activity: Activity){
        startActivity(Intent(this, activity::class.java))
    }

    open fun <T> handleResult(result: Result<T>?, success: (T?) -> Unit){
        when(result){
            is Result.Success<T> -> {
                logE("Success: ${result.data}")
                success(result.data)
            }
            is Result.Loading<T> -> {
                logE("Loading: ${result.loading}")
                if(result.loading) showLoader() else hideLoader()
            }
            is Result.Failure<T> -> {
                logE("Failure: ${result.errorMsg}")
                showToastMsg(result.errorMsg)
            }
            else -> Log.e("TEST","Else case: null")
        }
    }

    open fun collectFlow(collect: suspend () -> Unit){
        lifecycleScope.launch {
            collect()
        }
    }

}
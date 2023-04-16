package com.example.userstestapplication.ui.detail

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.userstestapplication.R
import com.example.userstestapplication.base.BaseActivity
import com.example.userstestapplication.databinding.ActivityDetailBinding
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefs
import com.example.userstestapplication.model.UsersData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailScreen : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private var userData: UsersData? = null

    override fun init() {
        getData()
        setData()
    }

    private fun getData() {
        userData = intent.getParcelableExtra("data")
    }

    private fun setData() {
        binding.apply {

            Glide.with(this@DetailScreen)
                .load(userData?.avatar ?: "")
                .placeholder(R.drawable.default_image_placeholder)
                .error(R.drawable.default_image_placeholder)
                .into(userPic)

            firstName.text = userData?.first_name ?: ""
            lastName.text = userData?.last_name ?: ""
            emailId.text = userData?.email ?: ""
        }
    }

    override fun getBindingLayout(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): DetailViewModel {
        return ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun setToolbarCountView(): TextView? {
        return binding.toolbarLL.appOpenedCountTV
    }

}
package com.example.userstestapplication.ui.home_listing

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.userstestapplication.base.BaseActivity
import com.example.userstestapplication.databinding.ActivityHomeListingBinding
import com.example.userstestapplication.di.repository_helper.shared_prefs.SharedPrefs
import com.example.userstestapplication.model.UsersData
import com.example.userstestapplication.ui.detail.DetailScreen
import com.example.userstestapplication.ui.home_listing.adapters.UsersAdapter
import com.example.userstestapplication.utils.VerticalItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeListingScreen : BaseActivity<ActivityHomeListingBinding, HomeViewModel>() {

    private lateinit var usersAdapter: UsersAdapter

    override fun init() {
        setAppOpenedCount()
        initRecyclerView()
        collectFlows()
    }

    private fun setAppOpenedCount(){
        viewModel?.let {
            it.saveAppOpenedCount()
            updateAppOpenedCount(it.getAppOpenedCount())
        }
    }

    private fun initRecyclerView() {
        usersAdapter = UsersAdapter{ userData ->
            goToActivity(DetailScreen(), userData)
        }
        binding.usersRV.apply {
            addItemDecoration(VerticalItemDecorator(18))
            adapter = usersAdapter
        }
    }

    private fun setUsersData(list: ArrayList<UsersData>){
        usersAdapter.refreshList(list)
    }

    private fun collectFlows() {
        viewModel?.let{ model ->
            collectFlow {
                model.mUsersFlow?.let{
                    it.collect {result->
                        handleResult(result){ resp ->
                            setUsersData(resp?.data ?: arrayListOf())
                        }
                    }
                }
            }
        }
    }

    override fun getBindingLayout(): ActivityHomeListingBinding {
        return ActivityHomeListingBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun setLoaderView(): View? {
        return binding.loaderLayout.root
    }

    override fun setToolbarCountView(): TextView? {
        return binding.toolbarLL.appOpenedCountTV
    }

}
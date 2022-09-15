package com.example.feature_main_screen.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.annotation.qualifiers.Main
import com.example.core.di.annotation.qualifiers.Vertical
import com.example.core.ui.BaseFragment
import com.example.core.utils.Config
import com.example.feature_main_screen.databinding.FragmentMainScreenBinding
import com.example.feature_main_screen.ui.adapters_and_delegates.delegation_adapters.MainDelegationAdapter
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.example.feature_main_screen.view_models.ComponentViewModel
import com.example.feature_main_screen.view_models.MainViewModel
import dagger.Lazy
import javax.inject.Inject

class FragmentMainScreen : BaseFragment<FragmentMainScreenBinding>() {

    @Inject internal lateinit var mainViewModelFactory: Lazy<MainViewModel.Factory>
    @[Inject Vertical] internal lateinit var verticalLayoutManagerProvider: LayoutManagerProvider
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory.get() }
    private val componentViewModel: ComponentViewModel by viewModels()
    private lateinit var mainDelegationAdapter: MainDelegationAdapter

    override fun initBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMainScreenBinding =
        FragmentMainScreenBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainScreenRecycler.apply {
            adapter = mainDelegationAdapter.buildAdapter()
            this.layoutManager = LinearLayoutManager(context)
            this.overScrollMode = View.OVER_SCROLL_NEVER
        }

        mainViewModel.apply {
            observeItems(this@FragmentMainScreen) { items ->
                mainDelegationAdapter.setItems(items = items)
            }
            // todo
            observeError(this@FragmentMainScreen) { error ->
                Log.d(Config.MAIN_TAG, "FragmentMainScreen: $error")
            }

            observeIsProgressBarShowed(this@FragmentMainScreen) { isShowed ->
                binding.progressBarBox.visibility = if (isShowed) VISIBLE else GONE
            }
            fetchData()
        }
    }

    @Inject internal fun init(
        mainDelegationAdapterFactory: MainDelegationAdapter.Factory
    ) {
        mainDelegationAdapter = mainDelegationAdapterFactory.create(
            navController = findNavController()
        )
    }
}
package com.example.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dagger.Component

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _viewBinding: B? = null
    private var _layoutManager: RecyclerView.LayoutManager? = null

    // may be IllegalStateException
    protected val binding get() = checkNotNull(_viewBinding)
    protected val layoutManager: RecyclerView.LayoutManager get() = checkNotNull(_layoutManager)

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B


    // todo: delete
    protected open fun initLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = initBinding(inflater = inflater, container = container)
        _layoutManager = initLayoutManager(binding.root.context)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
        _layoutManager = null
    }
}


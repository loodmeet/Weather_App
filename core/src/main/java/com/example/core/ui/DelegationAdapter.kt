package com.example.core.ui

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

interface DelegationAdapter<T> {

    fun buildAdapter(): ListDelegationAdapter<List<T>>
}


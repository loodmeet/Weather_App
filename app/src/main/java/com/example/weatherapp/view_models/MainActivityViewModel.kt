package com.example.weatherapp.view_models

import androidx.lifecycle.*

class MainActivityViewModel: ViewModel() {

    private val isMenuButtonClicked = MutableLiveData(false)
    private val isSettingsButtonClicked = MutableLiveData(false)

    private val isMenuVisible = MutableLiveData(false)
    private val isSettingsVisible = MutableLiveData(false)


    fun changeIsMenuButtonClickedState() {

        val isClicked = !isMenuButtonClicked.value!!
        isMenuButtonClicked.value = isClicked

        isMenuVisible.value = isClicked

        if (!isClicked) {
            isSettingsButtonClicked.value = false
            isSettingsVisible.value = false
        }

    }

    fun changeIsSettingsButtonClickedState() {

        val isClicked: Boolean = !isSettingsButtonClicked.value!!
        isSettingsButtonClicked.value = isClicked

        if (isClicked) {
            isSettingsVisible.value = true
            if (!isMenuButtonClicked.value!!) isMenuButtonClicked.value = true
            isMenuVisible.value = false
        }
    }

    fun observeIsMenuVisible(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isMenuVisible.observe(owner, observer)
    }

    fun observeIsSettingsVisible(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isSettingsVisible.observe(owner, observer)
    }

    fun observeIsMenuButtonClicked(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isMenuButtonClicked.observe(owner, observer)
    }

    fun observeIsSettingsButtonClicked(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isSettingsButtonClicked.observe(owner, observer)
    }

}




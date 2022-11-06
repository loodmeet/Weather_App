package com.example.feature_main_screen.view_models

import androidx.lifecycle.*
import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.ui.DisplayableItem
import com.example.core.utils.ItemsSortExecutor
import com.example.feature_main_screen.domain.use_cases.FetchDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainViewModel(
    private val fetchDataUseCase: FetchDataUseCase,
    private val itemsSortExecutor: ItemsSortExecutor,
    private val displayableItemsArray: DisplayableItemsProvider,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    // todo: mvi
    private val itemsLiveData = MutableLiveData<List<DisplayableItem>>()
    private val errorLiveData = MutableLiveData(false)
    private val isProgressBarShowedLiveData = MutableLiveData(true)

    fun observeItems(owner: LifecycleOwner, observer: Observer<List<DisplayableItem>>) {
        itemsLiveData.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<Boolean>) {
        errorLiveData.observe(owner, observer)
    }

    fun observeIsProgressBarShowed(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isProgressBarShowedLiveData.observe(owner, observer)
    }

    fun fetchData() {
        CoroutineScope(coroutineContext).launch {
            val fetchDataResult = fetchDataUseCase.execute()

            fetchDataResult.fold(
                onSuccess = { displayableItems ->

                    if (errorLiveData.value!!) errorLiveData.postValue(false)
                    val sortedItemsResult = itemsSortExecutor
                        .sortByRule(
                            items = displayableItems.toMutableList(),
                            rule = displayableItemsArray.items
                        )
                    sortedItemsResult.fold(
                        onSuccess = { sortedItems ->
                            itemsLiveData.postValue(sortedItems)
                            isProgressBarShowedLiveData.postValue(false)
                        },
                        onFailure = { errorLiveData.postValue(true) }
                    )
                },
                onFailure = { errorLiveData.postValue(true) }
            )

        }
    }

    class Factory @Inject constructor(
        private val fetchDataUseCase: FetchDataUseCase,
        private val itemsSortExecutor: ItemsSortExecutor,
        private val displayableItemsArray: DisplayableItemsProvider,
        @param: CoroutineContextDefault private val coroutineContext: CoroutineContext

    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                fetchDataUseCase = fetchDataUseCase,
                itemsSortExecutor = itemsSortExecutor,
                displayableItemsArray = displayableItemsArray,
                coroutineContext = coroutineContext
            ) as T
        }
    }

}

package com.example.core.di.dependensies

import com.example.core.ui.DisplayableItem
import kotlin.reflect.KClass

interface DisplayableItemsProvider {
    val items: Array<KClass<out DisplayableItem>>
}
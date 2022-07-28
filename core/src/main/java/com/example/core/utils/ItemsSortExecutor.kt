package com.example.core.utils

import com.example.core.di.annotation.CoroutineContextIO
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

interface ItemsSortExecutor {

    suspend fun <T : Any> sortByRule(
        items: MutableList<T>,
        vararg rule: KClass<out T>
    ): Result<List<T>>

    class Base @Inject constructor(
        @CoroutineContextIO private val coroutineContext: CoroutineContext
    ) : ItemsSortExecutor {

        override suspend fun <T : Any> sortByRule(
            items: MutableList<T>,
            vararg rule: KClass<out T>
        ): Result<List<T>> = withContext(coroutineContext) {

            return@withContext Result.success(
                value = List(
                    size = items.size,
                    init = { index ->
                        val item: T = items.find {
                            rule[index].isInstance(it)
                        } ?: return@withContext Result.failure(
                            exception = IllegalArgumentException(
                                "one of the declared types not found in the list " +
                                        "${rule[index].qualifiedName}"
                            )
                        )
                        items.removeAt(items.indexOf(item))
                        return@List item
                    })
            )
        }
    }
}
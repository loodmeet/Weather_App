package com.example.core.domain.use_case

import com.example.core.data.repository.Repository
import com.example.core.utils.Mapper
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @param UM UI Model
 * @param DOM Domain Model
 * @param DAM Data Model
 * */
abstract class UseCase<out UM : Any, in DOM : Any, out DAM : Any>(
    private val mapper: Mapper<DOM, UM>,
    private val repository: Repository<DOM, DAM>,
    private val coroutineContext: CoroutineContext
) {
    open suspend operator fun invoke(): Result<UM> = withContext(context = coroutineContext) {
        return@withContext try {
            Result.success(value = mapper.map(from = repository.fetchData()))
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}

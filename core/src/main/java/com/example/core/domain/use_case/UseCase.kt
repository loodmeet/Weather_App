package com.example.core.domain.use_case

import com.example.core.data.repository.Repository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.utils.Mapper
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @param DM Domain Model
 * @param UM UI Model
 * */
abstract class UseCase<in DM : Any, out UM : Any>(
    private val mapper: Mapper<DM, UM>,
    private val repository: Repository<DM>,
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext
) {
    open suspend operator fun invoke(): Result<UM> = withContext(context = coroutineContext) {
        return@withContext try {
            Result.success(value = mapper.map(from = repository.fetchData()))
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}
package com.example.core.domain.use_case

/**
 * @param UM UI Model
 * */
interface UseCase<out UM : Any> {

    suspend operator fun invoke(): Result<UM>
}
package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.data.repository.Repository
import com.example.core.domain.use_case.UseCase
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.domain.models.DomainModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @see UseCase
 * */
internal class FetchWeatherByDateUseCase<UM : DisplayableItem> @Inject constructor(
    domainModelsMapper: Mapper<@JvmSuppressWildcards DomainModel, @JvmSuppressWildcards UM>,
    repository: Repository<DomainModel>,
    coroutineContext: CoroutineContext
) : UseCase<DomainModel, UM>(
    mapper = domainModelsMapper,
    repository = repository,
    coroutineContext = coroutineContext
)
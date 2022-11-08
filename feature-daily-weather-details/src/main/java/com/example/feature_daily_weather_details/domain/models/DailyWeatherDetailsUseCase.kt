package com.example.feature_daily_weather_details.domain.models

import com.example.core.data.repository.Repository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.domain.use_case.UseCase
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DataModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @param UM UI Model
 * @see UseCase
 * @see Repository
 * @see DomainModel
 * @see DataModel
 * */
internal class DailyWeatherDetailsUseCase<UM : Any> @Inject constructor(
    @CoroutineContextIO coroutineContext: CoroutineContext,
    repository: Repository<DomainModel, DataModel>,
    mapper: Mapper<DomainModel, UM>
) : UseCase<UM, DomainModel, DataModel>(
    mapper = mapper,
    coroutineContext = coroutineContext,
    repository = repository
)
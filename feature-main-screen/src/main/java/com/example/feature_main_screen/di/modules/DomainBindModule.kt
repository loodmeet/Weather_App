package com.example.feature_main_screen.di.modules

import com.example.feature_main_screen.data.repository.MainRepositoryImpl
import com.example.feature_main_screen.di.annotations.FeatureMainScreen
import com.example.feature_main_screen.domain.repository.MainRepository
import com.example.feature_main_screen.domain.use_cases.FetchDataUseCase
import dagger.Binds
import dagger.Module

@Module
internal interface DomainBindModule {

    @[FeatureMainScreen Binds] fun bindMainRepository(repository: MainRepositoryImpl): MainRepository
    @[FeatureMainScreen Binds] fun bindFetchDataUseCase(useCase: FetchDataUseCase.Base): FetchDataUseCase
}
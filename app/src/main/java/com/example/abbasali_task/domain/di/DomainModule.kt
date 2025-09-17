package com.example.abbasali_task.domain.di

import com.example.abbasali_task.domain.usecase.PortfolioUseCase
import com.example.abbasali_task.domain.usecase.PortfolioUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindPortfolioUseCase(
        portfolioUseCaseImpl: PortfolioUseCaseImpl
    ): PortfolioUseCase

}
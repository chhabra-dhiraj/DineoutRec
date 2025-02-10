package io.github.chhabra_dhiraj.dineoutrec.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.chhabra_dhiraj.dineoutrec.data.repository.RestaurantsRepositoryImpl
import io.github.chhabra_dhiraj.dineoutrec.data.repository.UserPreferencesRepositoryImpl
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.RestaurantsRepository
import io.github.chhabra_dhiraj.dineoutrec.domain.repository.UserPreferencesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantsRepository(
        restaurantsRepositoryImpl: RestaurantsRepositoryImpl
    ): RestaurantsRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}
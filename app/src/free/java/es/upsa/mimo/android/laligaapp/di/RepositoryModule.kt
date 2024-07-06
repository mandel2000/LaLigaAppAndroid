package es.upsa.mimo.android.laligaapp.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import es.upsa.mimo.android.laligaapp.di.Free
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.network.ApiService

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @Free
    fun provideFreeTeamRepository(@ApplicationContext context: Context): TeamRepository {
        return FreeTeamsRepository(context)
    }

    @Provides
    @Free
    fun provideFreeStandingsRepository(@ApplicationContext context: Context): StandingRepository {
        return FreeStandingsRepository(context)
    }

    @Provides
    @Free
    fun provideFreeFixturesRepository(@ApplicationContext context: Context): FixtureRepository {
        return FreeFixturesRepository(context)
    }

    @Provides
    @Paid
    fun provideTeamsRepository() : TeamRepository? {
        return null
    }

    @Provides
    @Paid
    fun provideStandingsRepository() : StandingRepository? {
        return null
    }

    @Provides
    @Paid
    fun provideFixturesRepository() : FixtureRepository? {
        return null
    }
}
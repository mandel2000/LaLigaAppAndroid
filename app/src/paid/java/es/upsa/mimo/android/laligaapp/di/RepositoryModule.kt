package es.upsa.mimo.android.laligaapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import es.upsa.mimo.android.laligaapp.di.Paid
import es.upsa.mimo.android.laligaapp.network.ApiService
import es.upsa.mimo.android.laligaapp.repository.FixtureRepository
import es.upsa.mimo.android.laligaapp.repository.FixturesRepository
import es.upsa.mimo.android.laligaapp.repository.StandingRepository
import es.upsa.mimo.android.laligaapp.repository.StandingsRepository
import es.upsa.mimo.android.laligaapp.repository.TeamRepository
import es.upsa.mimo.android.laligaapp.repository.TeamsRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
 class RepositoryModule {

    @Provides
    @Free
    fun provideFreeTeamRepository(): TeamRepository? {
        return null
    }

    @Provides
    @Free
    fun provideFreeStandingsRepository(): StandingRepository? {
        return null
    }

    @Provides
    @Free
    fun provideFreeFixturesRepository(): FixtureRepository? {
        return null
    }
    @Provides
    @Paid
    fun provideTeamsRepository(apiService: ApiService) : TeamRepository {
        return TeamsRepository(apiService)
    }

    @Provides
    @Paid
    fun provideStandingsRepository(apiService: ApiService) : StandingRepository {
        return StandingsRepository(apiService)
    }

    @Provides
    @Paid
    fun provideFixturesRepository(apiService: ApiService) : FixtureRepository {
        return FixturesRepository(apiService)
    }
}
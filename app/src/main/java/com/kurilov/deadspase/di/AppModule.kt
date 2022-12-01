package com.kurilov.deadspase.di

import android.app.Application
import android.content.Context
import com.kurilov.deadspase.data.api.schedule.ScheduleApiService
import com.kurilov.deadspase.data.api.schedule.ScheduleRetrofitUtils
import com.kurilov.deadspase.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return AppDatabase.buildDatabase(app)
    }

    @Singleton
    @Provides
    //todo all Api Service in one interface?
    fun provideScheduleApiService(): ScheduleApiService {
        return ScheduleRetrofitUtils.apiService
    }

}
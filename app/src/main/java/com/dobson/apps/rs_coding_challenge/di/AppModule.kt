package com.dobson.apps.rs_coding_challenge.di

import android.app.Application
import androidx.room.Room
import com.dobson.apps.rs_coding_challenge.model.data.local.DriverRouteDatabase
import com.dobson.apps.rs_coding_challenge.model.data.remote.apiservice.DriverRouteApi
import com.dobson.apps.rs_coding_challenge.model.repository.DriverRouteRepository
import com.dobson.apps.rs_coding_challenge.model.repository.DriverRouteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(DriverRouteApi.BASE_URL)
        .build()

    @Provides
    fun providesShowsApi(retrofit: Retrofit): DriverRouteApi = retrofit.create(DriverRouteApi::class.java)

    @Provides
    fun providesDatabase(app: Application): DriverRouteDatabase {
        return Room.databaseBuilder(
            app,
            DriverRouteDatabase::class.java,
            "drivers_routes_db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesRepository(
        api: DriverRouteApi,
        db: DriverRouteDatabase
    ): DriverRouteRepository {
        return DriverRouteRepositoryImpl(api, db)
    }
}
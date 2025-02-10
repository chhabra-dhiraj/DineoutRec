package io.github.chhabra_dhiraj.dineoutrec.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.chhabra_dhiraj.dineoutrec.BuildConfig
import io.github.chhabra_dhiraj.dineoutrec.data.remote.RestaurantsApi
import io.github.chhabra_dhiraj.dineoutrec.data.util.RestaurantsInfoMapperConstants.SECTION_CLASS_DISCRIMINATOR
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // For Remote WEB Apis
    private const val WEB_API_VERSION = "v1"
    private const val BASE_URL = "https://restaurant-api.wolt.com/$WEB_API_VERSION/"
    private const val DEFAULT_MEDIA_TYPE = "application/json; charset=UTF8"

    // For Data Store User Preferences
    private const val USER_PREFERENCES_DATA_STORE = "user_preferences"

    @Provides
    @Singleton
    fun provideRestaurantsApi(): RestaurantsApi {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val networkJson = Json {
            ignoreUnknownKeys = true
            classDiscriminator = SECTION_CLASS_DISCRIMINATOR
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                networkJson.asConverterFactory(DEFAULT_MEDIA_TYPE.toMediaType())
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES_DATA_STORE) }
    )
}
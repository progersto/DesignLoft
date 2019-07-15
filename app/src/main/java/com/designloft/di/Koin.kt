package com.designloft.di

import android.content.Context
import com.designloft.data.PreferencesManager
import com.designloft.network.ApiInterface
import com.designloft.utils.BASE_URSL
import com.google.gson.Gson
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModule = module {
//    viewModel { AuthViewModel(get()) }

}

private val networkModule = module {
    single { Retrofit.Builder()
            .baseUrl(BASE_URSL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build().create(ApiInterface::class.java)}
}

private val dataModule = module {
    single { get<Context>().defaultSharedPreferences }
    single { PreferencesManager(get()) }
//    single { AuthDataManagerImpl(get(), get(), get()) as AuthDataManager }
//    single { MainDataManagerImpl(get(), get()) as MainDataManager }
}

val appModules  = mutableListOf(viewModelModule, networkModule, dataModule)
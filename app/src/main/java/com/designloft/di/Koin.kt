package com.designloft.di

import android.content.Context
import androidx.room.Room
import com.designloft.data.MainDataManager
import com.designloft.data.PreferencesManager
import com.designloft.database.DesignLoftDataBase
import com.designloft.network.ApiInterface
import com.designloft.repository.ModelRepository
import com.designloft.ui.main.MainViewModel
import com.designloft.utils.BASE_URSL
import com.google.gson.Gson
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModule = module {
    viewModel { MainViewModel(get()) }

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
    single { MainDataManager(get(), get()) }
    single { ModelRepository(get(), get()) }
    single { Room.databaseBuilder(get(), DesignLoftDataBase::class.java, "data_base").build() }
    single { get<DesignLoftDataBase>().getModelDao() }
}

val appModules  = mutableListOf(viewModelModule, networkModule, dataModule)
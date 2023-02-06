package com.example.quizapplication.di

import com.example.quizapplication.manager.QuizAssetManager
import com.example.quizapplication.repository.QuizRepository
import com.example.quizapplication.viewmodel.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ConfigurationClass {
    fun modules() = repositoryModule + viewModelModule + assetManager
}

val repositoryModule = module {
    single{ QuizRepository(get()) }
}

val viewModelModule = module {
    single { QuizViewModel(get()) }
}

val assetManager = module {
    single { QuizAssetManager(androidContext()) }
}
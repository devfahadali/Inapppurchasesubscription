package com.example.inapppurchasesubscription.di

import com.example.inapppurchasesubscription.SubscriptionViewModel
import com.example.inapppurchasesubscription.data.repos.SubscriptionRepoImpl
import com.example.inapppurchasesubscription.domain.repository.SubscriptionRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */

val dependencyModules = mutableListOf<Module>()

val modules= module {

    single<SubscriptionRepository> {
        SubscriptionRepoImpl(androidContext())
    }
    viewModel{

        SubscriptionViewModel(get ())

    }
}
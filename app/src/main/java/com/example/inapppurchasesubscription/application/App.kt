package com.example.inapppurchasesubscription.application

import android.app.Application
import com.example.inapppurchasesubscription.di.dependencyModules
import com.example.inapppurchasesubscription.di.modules
import com.example.inapppurchasesubscription.domain.repository.SubscriptionRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */

class App: Application() {

    private val subscriptionRepository: SubscriptionRepository by inject()


    override fun onCreate() {
        super.onCreate()

//        koinApplication {
//            addKoinModule()
//
//        }
        startKoin {
            androidContext(this@App)
            modules (modules)
        }
//        subscriptionRepository.launchQuery()
    }


    fun addKoinModule(){
        dependencyModules.add(modules)

    }

}

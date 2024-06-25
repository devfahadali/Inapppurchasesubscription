package com.example.inapppurchasesubscription.domain.repository

import androidx.fragment.app.FragmentActivity
import com.android.billingclient.api.ProductDetails

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */

interface SubscriptionRepository {
    fun startConnection()
    fun launchQuery()

    fun getSubscriptions():Result<List<ProductDetails>>
    fun launchBillingFlow(activity: FragmentActivity, productId: String)

    suspend fun verifyPurchaseQuery(productId:String, purchaseToken:String,onComplete: (accessToken: Long?) -> Unit)
    fun restorePurchases(onComplete: (accessToken: Long?) -> Unit)

}
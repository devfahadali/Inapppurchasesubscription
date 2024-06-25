package com.example.inapppurchasesubscription.data.repos

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.example.inapppurchasesubscription.constants.WEEKLY_PLAN_PRODUCT_ID
import com.example.inapppurchasesubscription.constants.YEARLY_PLAN_PRODUCT_ID
import com.example.inapppurchasesubscription.domain.repository.SubscriptionRepository
import com.example.inapppurchasesubscription.getStringFromName
import com.example.inapppurchasesubscription.models.Purchased

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */

class SubscriptionRepoImpl(
    val context: Context,
) : PurchasesUpdatedListener,  SubscriptionRepository {

    private var productDetails: List<ProductDetails> = ArrayList()
    private var restoreCheckCount: Int = 0
    private var restoreLatestPurchaseId: String? = null
    private var restoreLatestPurchaseToken: String? = null
    private var restoreLatestExpiry: Long? = 0
    private var errorMessage: String = "No Internet Connection"


    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .enablePendingPurchases()
        .build()

    init {
        startConnection()
    }

    override fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    launchQuery()
                }
            }

            override fun onBillingServiceDisconnected() {
                startConnection()
            }
        })
    }

    override fun launchQuery() {
        if (productDetails.isEmpty()) {
            val params = getParams(
                getProductPlan(WEEKLY_PLAN_PRODUCT_ID),
                getProductPlan(YEARLY_PLAN_PRODUCT_ID)
            )
            billingClient.queryProductDetailsAsync(
                params
            ) { response: BillingResult, details: List<ProductDetails> ->
                if (response.responseCode == BillingClient.BillingResponseCode.OK) {
                    this.productDetails = details
                } else {
                    errorMessage = response.debugMessage
                }
            }
        }
    }

    private fun getParams(vararg products: QueryProductDetailsParams.Product): QueryProductDetailsParams {
        return QueryProductDetailsParams.newBuilder()
            .setProductList(products.toList())
            .build()
    }

    private fun getProductPlan(id: String): QueryProductDetailsParams.Product {
        return QueryProductDetailsParams.Product.newBuilder()
            .setProductId(id)
            .setProductType(BillingClient.ProductType.SUBS)
            .build()
    }

    override fun getSubscriptions(): Result<List<ProductDetails>> {
        return if (productDetails.isNotEmpty()) {
            Result.success(productDetails)
        } else {
            launchQuery()
            Result.failure(Exception(errorMessage))
        }
    }

    override fun launchBillingFlow(
        activity: FragmentActivity,
        productId: String
    ) {
        productDetails.find { it.productId == productId }?.let {
            it.subscriptionOfferDetails?.first()?.offerToken?.let { token ->
                val productDetailsParamsList = listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(it)
                        .setOfferToken(token)
                        .build()
                )
                val billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .build()
                billingClient.launchBillingFlow(activity, billingFlowParams)
            }
        }
    }



    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                verifySubPurchase(purchase)
            }
        }
    }

    private fun verifySubPurchase(purchases: Purchase) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchases.purchaseToken)
            .build()
        billingClient.acknowledgePurchase(
            acknowledgePurchaseParams
        ) { billingResult: BillingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {

                val intent = Intent(context.getStringFromName("subscription_valid")).also {

                    it.putExtra(Purchased::acknowledged.name, purchases.isAcknowledged)
                    it.putExtra(Purchased::autoRenewing.name, purchases.isAutoRenewing)
                    it.putExtra(Purchased::orderId.name, purchases.orderId)
                    it.putExtra(Purchased::packageName.name, purchases.packageName)
                    it.putExtra(
                        Purchased::productId.name, purchases.products.first()
                    )
                    it.putExtra(Purchased::purchaseState.name, purchases.purchaseState)
                    it.putExtra(Purchased::purchaseTime.name, purchases.purchaseTime)
                    it.putExtra(Purchased::purchaseToken.name, purchases.purchaseToken)
                    it.putExtra(Purchased::quantity.name, purchases.quantity)


                }

                Log.d("TAGG", "Purchase Token: " + purchases.purchaseToken)
                Log.d("TAGG", "Purchase Time: " + purchases.purchaseTime)
                Log.d("TAGG", "Purchase OrderID: " + purchases.orderId)

                context.sendBroadcast(intent)

            }
        }


    }


    override suspend fun verifyPurchaseQuery(
        productId: String,
        purchaseToken: String,
        onComplete: (accessToken: Long?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun restorePurchases(onComplete: (accessToken: Long?) -> Unit) {
        TODO("Not yet implemented")
    }
}
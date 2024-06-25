package com.example.inapppurchasesubscription.models

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */

data class Purchased(
    var acknowledged: Boolean,
    var autoRenewing: Boolean,
    var orderId: String,
    var packageName: String,
    var productId: String,
    var purchaseState: Int,
    var purchaseTime: Long,
    var purchaseToken: String,
    var quantity: Int
)
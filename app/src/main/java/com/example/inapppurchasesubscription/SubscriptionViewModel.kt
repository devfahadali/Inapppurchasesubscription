package com.example.inapppurchasesubscription

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.ProductDetails
import com.example.inapppurchasesubscription.domain.repository.SubscriptionRepository
import com.example.inapppurchasesubscription.models.Purchased
import kotlinx.coroutines.launch

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */


class SubscriptionViewModel(private val repository: SubscriptionRepository) : ViewModel() {

    private val _productDetails: MutableLiveData<Result<List<ProductDetails>>> by lazy {
        MutableLiveData()
    }
    val productDetails: LiveData<Result<List<ProductDetails>>>
        get() = _productDetails

    fun getPurchasePlan() = viewModelScope.launch {
        _productDetails.value = repository.getSubscriptions()
        _productDetails
    }



    fun purchaseLaunch(
        activity: FragmentActivity,
        productId: String
    ) = viewModelScope.launch {
        repository.launchBillingFlow(activity, productId)
    }


}
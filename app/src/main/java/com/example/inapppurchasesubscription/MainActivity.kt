package com.example.inapppurchasesubscription

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inapppurchasesubscription.screens.InAppPurchaseScreen
import com.example.inapppurchasesubscription.ui.theme.InAppPurchaseSubscriptionTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

//    val subscriptionViewModel: SubscriptionViewModel = viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        subscriptionViewModel.getPurchasePlan()
        setupObserver()

        setContent {
            InAppPurchaseSubscriptionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GreetingPreview()
                    InAppPurchaseScreen()
                }
            }
        }
    }
}

 fun setupObserver() {

//     subscriptionViewModel.purchasedValue.observe {
//        Log.d("purchasedValue", it.toString())
//        if (it.productId != "") {
//            subscriptionViewModel.verifyPurchaseQuery(it) { checkVerify ->
//                dataViewModel.getBoolean("isPro") { isPro ->
//                    if (!isPro) {
//                        dataViewModel.put("isPro", checkVerify)
//                        dataViewModel.getBoolean("isPro")
//                    }
//                }
//            }
//        }

//    }


}
//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        Button(onClick = { /*TODO*/ }) {

            Text(text = "InApp Purchase")

        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Button(onClick = { /*TODO*/ }) {

            Text(text = "Subscription")
        }
    }
}
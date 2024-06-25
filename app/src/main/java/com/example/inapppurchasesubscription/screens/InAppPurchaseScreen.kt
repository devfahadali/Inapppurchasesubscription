package com.example.inapppurchasesubscription.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inapppurchasesubscription.R
import com.example.inapppurchasesubscription.SubscriptionViewModel
import com.example.inapppurchasesubscription.ui.theme.InAppPurchaseSubscriptionTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 22/06/2024
 */

@Preview(showBackground = true)
@Composable
fun InAppPurchaseScreen() {

    val subscriptionViewModel: SubscriptionViewModel = koinViewModel()
//    val subscriptionViewModel: SubscriptionViewModel = viewModel()
//


    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Try Pro for Free ",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().clickable {
                subscriptionViewModel.getPurchasePlan()
            }
        )

        Spacer(modifier = Modifier.padding(top = 15.dp))

        Image(painterResource(id = R.drawable.ic_crown), "", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(top = 15.dp))

        Text(text = "Get unlimited access to all\npremium features for Free ")
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ImageText(text = "No Ads")
        ImageText(text = "Unlimited Dialogues")
        ImageText(text = "Higher Word Limit")

        ItemSubscription()


        Card(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Continue",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            )
        }


    }


}

@Composable
fun ImageText(text: String) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp, top = 4.dp)
    ) {

        Image(painter = painterResource(id = R.drawable.ic_tick), contentDescription = "")
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(text = text)
    }

}

@Composable
fun ItemSubscription() {

    Card(
        elevation = CardDefaults.elevatedCardElevation(), modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Column {
                Text(text = "3 Days Free Trial")
                Text(text = "Cancel Anytime,then $4.99 Weakly")
            }
            Spacer(modifier = Modifier.padding(start = 15.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_tick),
                modifier = Modifier.size(40.dp),
                contentDescription = ""
            )
        }
    }
}
package io.github.chhabra_dhiraj.dineoutrec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.ProximityRestaurantVenuesScreen
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DineoutRecTheme {
                ProximityRestaurantVenuesScreen()
            }
        }
    }
}
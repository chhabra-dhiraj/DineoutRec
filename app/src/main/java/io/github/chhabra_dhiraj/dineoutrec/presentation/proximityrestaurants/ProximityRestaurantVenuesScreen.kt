package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProximityRestaurantVenuesScreen() {
    val viewModel = viewModel<ProximityRestaurantVenuesViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProximityRestaurantVenuesScreenUI(
        state = state,
        onEvent = viewModel::onEvent
    )
}

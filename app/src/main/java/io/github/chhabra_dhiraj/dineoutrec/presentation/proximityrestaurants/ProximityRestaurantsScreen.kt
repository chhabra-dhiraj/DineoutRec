package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProximityRestaurantsScreen() {
    // TODO: Find lazy ('by' one) initialization
    val viewModel = viewModel<ProximityRestaurantsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProximityRestaurantsScreenUI(
        state = state,
        onEvent = viewModel::onEvent
    )
}

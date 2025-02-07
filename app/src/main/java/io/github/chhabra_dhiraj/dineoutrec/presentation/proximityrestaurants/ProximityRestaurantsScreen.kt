package io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.BasicScreenHeader
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.EmptyListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ErrorListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.ListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.component.LoadingListPlaceholder
import io.github.chhabra_dhiraj.dineoutrec.presentation.proximityrestaurants.component.ProximityRestaurantList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProximityRestaurantsScreen() {
    // TODO: Find lazy ('by' one) initialization
    val viewModel = viewModel<ProximityRestaurantsViewmodel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pullToRefreshState = rememberPullToRefreshState()
    val onRefresh = {
        viewModel.onEvent(ProximityRestaurantsEvent.OnManualRefreshProximityRestaurants)
    }
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = onRefresh,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(
                        top = dimensionResource(
                            // TODO: Find a better way to space down pull to refresh
                            id = R.dimen.spacing40
                        )
                    ),
                isRefreshing = state.isLoading,
                state = pullToRefreshState
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pullToRefresh(
                    state = rememberPullToRefreshState(),
                    isRefreshing = state.isLoading,
                    onRefresh = onRefresh,
                    enabled = false
                ),
            topBar = {
                TopAppBar(
                    title = {
                        BasicScreenHeader(
                            modifier = Modifier
                                .fillMaxWidth(),
                            title = stringResource(id = R.string.app_name),
                        )
                    })
            }
        ) { contentPadding ->
            ProximityRestaurantsBody(
                modifier = Modifier
                    .padding(contentPadding),
                state = state
            )
        }
    }
}

@Composable
fun ProximityRestaurantsBody(
    modifier: Modifier = Modifier,
    state: ProximityRestaurantsState
) {
    Box(modifier = modifier) {
        state.proximityRestaurants?.let { restaurants ->
            if (restaurants.isNotEmpty()) {
                ProximityRestaurantList(
                    restaurants = restaurants
                )
            } else {
                ListPlaceholder(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            dimensionResource(
                                id = R.dimen.spacing32
                            )
                        )
                        .verticalScroll(// TODO: Find a better way to trigger pull to refresh
                            state = rememberScrollState()
                        ),
                    placeholder = {
                        EmptyListPlaceholder(
                            // TODO: Refactor this by introducing uiState
                            title = state.noVenueSection?.title ?: "",
                            subtitle = state.noVenueSection?.description ?: ""
                        )
                    })
            }
        } ?: run {
            if (state.isLoading) {
                ListPlaceholder(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            dimensionResource(
                                id = R.dimen.spacing32
                            )
                        )
                        .verticalScroll(// TODO: Find a better way to trigger pull to refresh
                            state = rememberScrollState()
                        ),
                    placeholder = {
                        LoadingListPlaceholder()
                    })
            } else {
                state.error?.let {
                    ListPlaceholder(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                dimensionResource(
                                    id = R.dimen.spacing32
                                )
                            )
                            .verticalScroll(// TODO: Find a better way to trigger pull to refresh
                                state = rememberScrollState()
                            ),
                        placeholder = {
                            ErrorListPlaceholder(
                                error = it.asString()
                            )
                        })
                }
            }
        }
    }
}
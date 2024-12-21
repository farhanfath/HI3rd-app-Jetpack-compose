package basic.training.jetpack.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import basic.training.jetpack.data.model.Valkyrie
import basic.training.jetpack.di.Injection
import basic.training.jetpack.ui.components.ItemValkyrie
import basic.training.jetpack.utils.UiState
import basic.training.jetpack.viewmodel.ValkyrieViewModel

@Composable
fun HomeScreen(
    viewModel: ValkyrieViewModel = viewModel(factory = Injection.provideVieModelFactory()),
    navToDetail : (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        listState.animateScrollToItem(index = 0)
    }

    Surface(modifier = Modifier
        .fillMaxSize(),
    ) {
        when(uiState) {
            is UiState.Loading -> {
                Text(text = "Loading...")
            }
            is UiState.Success -> {
                val valkyrieList = (uiState as UiState.Success<List<Valkyrie>>).data

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    items(valkyrieList) { valkyrie ->
                        ItemValkyrie(
                            modifier = Modifier
                                .clickable { navToDetail(valkyrie.id) },
                            item = valkyrie
                        )
                    }
                }
            }
            is UiState.Error -> {
                Text(text = (uiState as UiState.Error).errMsg)
            }
        }
    }
}


package basic.training.jetpack.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import basic.training.jetpack.data.model.Valkyrie
import basic.training.jetpack.di.Injection
import basic.training.jetpack.ui.components.ImageLoader
import basic.training.jetpack.utils.UiState
import basic.training.jetpack.viewmodel.ValkyrieViewModel
import coil.compose.AsyncImage

@Composable
fun DetailScreen(valkyrieId: String, viewModel: ValkyrieViewModel = viewModel(factory = Injection.provideVieModelFactory())) {

    // Memuat Valkyrie berdasarkan ID
    LaunchedEffect(key1 = valkyrieId) {
        viewModel.selectValkyrie(valkyrieId)
    }

    val uiState by viewModel.selectedValkyrie.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val valkyrie = (uiState as UiState.Success<Valkyrie>).data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageLoader(
                    modifier = Modifier.size(300.dp),
                    valkyrie.image
                )
                Text(
                    text = valkyrie.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = valkyrie.weapon,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (uiState as UiState.Error).errMsg,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
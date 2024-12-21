package basic.training.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import basic.training.jetpack.data.model.Valkyrie
import basic.training.jetpack.data.repository.ValkyrieRepository
import basic.training.jetpack.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ValkyrieViewModel(private val repository: ValkyrieRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Valkyrie>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Valkyrie>>> get() = _uiState

    private val _selectedValkyrie = MutableStateFlow<UiState<Valkyrie>>(UiState.Loading)
    val selectedValkyrie: StateFlow<UiState<Valkyrie>> get() = _selectedValkyrie

    init {
        fetchValkyrie()
    }

    private fun fetchValkyrie() {
        viewModelScope.launch {
            try {
                val valkyries = repository.getValkyrie()
                _uiState.value = UiState.Success(valkyries)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun selectValkyrie(id: String) {
        viewModelScope.launch {
            try {
                val valkyrie = repository.getValkyrieById(id)
                if (valkyrie != null) {
                    _selectedValkyrie.value = UiState.Success(valkyrie)
                } else {
                    _selectedValkyrie.value = UiState.Error("Valkyrie not found")
                }
            } catch (e: Exception) {
                _selectedValkyrie.value = UiState.Error(e.message.toString())
            }
        }
    }

}
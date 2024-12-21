package basic.training.jetpack.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import basic.training.jetpack.data.repository.ValkyrieRepository
import basic.training.jetpack.viewmodel.ValkyrieViewModel

class ViewModelFactory(private val repository: ValkyrieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ValkyrieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ValkyrieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
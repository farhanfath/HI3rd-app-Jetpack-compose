package basic.training.jetpack.di

import basic.training.jetpack.data.repository.ValkyrieRepository
import basic.training.jetpack.utils.ViewModelFactory

object Injection {

    private fun provideRepository(): ValkyrieRepository {
        return ValkyrieRepository()
    }
    fun provideVieModelFactory(): ViewModelFactory {
        return ViewModelFactory(
            repository = provideRepository()
        )
    }
}
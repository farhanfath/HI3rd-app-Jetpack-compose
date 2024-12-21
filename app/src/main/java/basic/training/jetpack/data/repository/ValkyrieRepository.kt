package basic.training.jetpack.data.repository

import basic.training.jetpack.data.model.Valkyrie
import basic.training.jetpack.utils.Constants

class ValkyrieRepository {
    fun getValkyrie(): List<Valkyrie> = Constants.listValkyrie

    fun getValkyrieById(id: String): Valkyrie? {
        return getValkyrie().find { valkyrie ->
            valkyrie.id == id
        }
    }
}
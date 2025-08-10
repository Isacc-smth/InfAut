package ctn.infaut.contollers

import kotlinx.serialization.Serializable

@Serializable
data class Docente(
    var id: Int,
    var nombre: String,
    var apellido: String,
    var ci: Int,
)
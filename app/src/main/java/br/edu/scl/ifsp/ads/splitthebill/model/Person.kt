package br.edu.scl.ifsp.ads.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val nome: String,
    val valorGasto: Double,
    var debito: Double,
    val descricao: String
) : Parcelable {
    // A secondary constructor to help in creating instances
    constructor(id: Int, nome: String, valorGasto: String, debito: String, descricao: String)
            : this(id, nome, valorGasto.toDouble(), debito.toDouble(), descricao)
}

package br.edu.scl.ifsp.ads.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val nome: String,
    val valorGasto: Double,
    val valorGasto2: Double,
    val valorGasto3: Double,
    val totalGasto: Double,
    var debito: Double,
    val descricao: String,
    val descricao2: String,
    val descricao3: String,
    val itensComprados: List<String>
) : Parcelable {
    constructor(id: Int,
                nome: String,
                valorGasto: String,
                valorGasto2: String,
                valorGasto3: String,
                totalGasto: String,
                debito: String,
                descricao: String,
                descricao2: String,
                descricao3: String,
                itensComprados: List<String>)
            : this(id, nome, valorGasto.toDouble(), valorGasto2.toDouble(), valorGasto3.toDouble(),
        totalGasto.toDouble(), debito.toDouble(), descricao, descricao2, descricao3, itensComprados)
}

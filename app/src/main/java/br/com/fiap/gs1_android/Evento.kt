package br.com.fiap.gs1_android

data class Evento(
    val local: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val data: String,
    val pessoasAfetadas: Int
)
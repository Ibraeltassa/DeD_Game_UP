package models

import services.RacaStrategy // Certifique-se de importar a interface RacaStrategy

class Personagem(
    val nome: String,
    val raca: RacaStrategy, // Mude para RacaStrategy
    var forca: Int,
    var destreza: Int,
    var constituicao: Int,
    var inteligencia: Int,
    var sabedoria: Int,
    var carisma: Int
) {
    // Modificador de Constituição
    private val modificadorConstituicao: Int
        get() = (constituicao - 10) / 2

    // Cálculo dos pontos de vida (10 + modificador de Constituição)
    val pontosDeVida: Int
        get() = 10 + modificadorConstituicao

    // Aplica o bônus racial
    fun aplicarBonusRacial() {
        val atributos = Atributos(forca, destreza, constituicao, inteligencia, sabedoria, carisma)
        raca.aplicarBonus(atributos)
        // Atualiza os atributos do personagem
        forca = atributos.forca
        destreza = atributos.destreza
        constituicao = atributos.constituicao
        inteligencia = atributos.inteligencia
        sabedoria = atributos.sabedoria
        carisma = atributos.carisma
    }
}

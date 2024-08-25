import models.Personagem
import services.Elfo
import services.Humano
import models.Anao
import services.DistribuicaoAtributos
import models.Atributos

fun main() {

    // Distribuição de pontos
    val atributos = Atributos(forca = 10, destreza = 10, constituicao = 10, inteligencia = 10, sabedoria = 10, carisma = 10)
    val distribuicao = DistribuicaoAtributos()

    if (distribuicao.distribuirPontos(atributos)) {
        println("Distribuição de pontos bem-sucedida!")
    } else {
        println("Distribuição de pontos excede o máximo permitido.")
    }


    // Criando personagens com bônus raciais aplicados
    val elfo = Personagem(
        nome = "Legolas",
        raca = Elfo(), // Passa a instância de Elfo
        forca = 10,
        destreza = 10,
        constituicao = 10,
        inteligencia = 10,
        sabedoria = 10,
        carisma = 10
    )
    elfo.aplicarBonusRacial()

    val humano = Personagem(
        nome = "Aragorn",
        raca = Humano(), // Passa a instância de Humano
        forca = 10,
        destreza = 10,
        constituicao = 10,
        inteligencia = 10,
        sabedoria = 10,
        carisma = 10
    )
    humano.aplicarBonusRacial()

    val anao = Personagem(
        nome = "Gimli",
        raca = Anao(), // Passa a instância de Anao
        forca = 10,
        destreza = 10,
        constituicao = 10,
        inteligencia = 10,
        sabedoria = 10,
        carisma = 10
    )
    anao.aplicarBonusRacial()

    // Exibindo informações dos personagens
    listOf(elfo, humano, anao).forEach { personagem ->
        println("Personagem criado:")
        println("Nome: ${personagem.nome}")
        println("Força: ${personagem.forca}")
        println("Destreza: ${personagem.destreza}")
        println("Constituição: ${personagem.constituicao}")
        println("Inteligência: ${personagem.inteligencia}")
        println("Sabedoria: ${personagem.sabedoria}")
        println("Carisma: ${personagem.carisma}")
        println("Pontos de Vida: ${personagem.pontosDeVida}")
        println()
    }
}

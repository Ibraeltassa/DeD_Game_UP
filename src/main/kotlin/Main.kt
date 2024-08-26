import models.Personagem
import models.Atributos
import services.DistribuicaoAtributos
import services.Elfo
import services.Humano
import models.Anao
import models.Orc
import models.Gnomo
import models.MeioElfo
import services.RacaStrategy

fun main() {
    println("\n**Welcome to DUNGEONS & DRAGONS Game**")

    // Instância de DistribuicaoAtributos para usar a tabela de pontos
    val distribuicao = DistribuicaoAtributos()

    // Mostrar a tabela de pontos antes de criar os personagens
    distribuicao.mostrarTabelaPontos()

    // Função para escolher a raça do personagem
    fun escolherRaca(): RacaStrategy {
        println("\nEscolha a raça do personagem:")
        println("1 - Elfo")
        println("2 - Humano")
        println("3 - Anão")
        println("4 - Orc")
        println("5 - Gnomo")
        println("6 - Meio-Elfo")
        print("Digite o número correspondente à raça escolhida: ")
        return when (readLine()) {
            "1" -> Elfo()
            "2" -> Humano()
            "3" -> Anao()
            "4" -> Orc()
            "5" -> Gnomo()
            "6" -> MeioElfo()
            else -> {
                println("Opção inválida. Humano selecionado por padrão.")
                Humano()
            }
        }
    }


    // Função para escolher os atributos do personagem
    fun escolherAtributos(): Atributos {
        val atributos = Atributos()
        var pontosRestantes = distribuicao.pontosTotais

        fun setAtributo(nome: String): Int {
            while (true) {
                println("Escolha o valor para $nome (8-15). Pontos restantes: $pontosRestantes")
                val input = readLine()?.toIntOrNull()
                val custo = input?.let { distribuicao.custoPontos.getOrDefault(it, 0) } ?: 0
                if (input != null && input in 8..15 && (pontosRestantes - custo) >= 0) {
                    pontosRestantes -= custo
                    return input
                } else {
                    println("Valor inválido ou pontos insuficientes! O valor para $nome deve estar entre 8 e 15 e você deve ter pontos suficientes.")
                }
            }
        }

        atributos.forca = setAtributo("Força")
        atributos.destreza = setAtributo("Destreza")
        atributos.constituicao = setAtributo("Constituição")
        atributos.inteligencia = setAtributo("Inteligência")
        atributos.sabedoria = setAtributo("Sabedoria")
        atributos.carisma = setAtributo("Carisma")

        return atributos
    }

    // Função para criar personagem com entrada do usuário
    fun criarPersonagem(): Personagem {
        val raca = escolherRaca()
        val atributos = escolherAtributos()

        val pontosGastos = atributos.totalDePontos().let { total ->
            atributos.forca.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.destreza.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.constituicao.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.inteligencia.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.sabedoria.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.carisma.let { distribuicao.custoPontos.getOrDefault(it, 0) }
        }

        if (pontosGastos > distribuicao.pontosTotais) {
            throw IllegalArgumentException("Distribuição de pontos excede o máximo permitido.")
        }

        val personagem = Personagem(
            nome = "Personagem sem nome",
            raca = raca,
            forca = atributos.forca,
            destreza = atributos.destreza,
            constituicao = atributos.constituicao,
            inteligencia = atributos.inteligencia,
            sabedoria = atributos.sabedoria,
            carisma = atributos.carisma
        )

        personagem.aplicarBonusRacial()

        // Mostrar os pontos utilizados e restantes
        val pontosRestantes = distribuicao.pontosTotais - pontosGastos
        println("\nPontos totais gastos: $pontosGastos")
        println("Pontos restantes: $pontosRestantes")

        // Criando e exibindo o personagem
        println("\nPersonagem criado:")
        println("Raça: ${personagem.raca::class.simpleName}")
        println("Força: ${personagem.forca}")
        println("Destreza: ${personagem.destreza}")
        println("Constituição: ${personagem.constituicao}")
        println("Inteligência: ${personagem.inteligencia}")
        println("Sabedoria: ${personagem.sabedoria}")
        println("Carisma: ${personagem.carisma}")
        println("Pontos de Vida: ${personagem.pontosDeVida}")

        // Mostrar bônus racial
        personagem.mostrarBonusRacial()

        return personagem
    }

    criarPersonagem()
}

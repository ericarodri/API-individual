package SPTech.API.individual.Dominio



import jakarta.persistence.*

import jakarta.validation.constraints.*
import java.time.LocalDate

@Entity
data class Adaptacao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idAdaptacao: Int,
    var nomeAdaptacao: String,
    var tituloLivro: String,
    var diretor:String,
    var tipoAdaptacao:String,
    var dataLancamento: LocalDate,
    @ManyToOne
    @JoinColumn(name = "fk_livro")
    var livro: Livro
){
}
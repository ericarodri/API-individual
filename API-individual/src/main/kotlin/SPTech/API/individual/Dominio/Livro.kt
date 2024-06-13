package SPTech.API.individual.Dominio


import jakarta.persistence.*
import java.time.LocalDate



@Entity
class Livro (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idLivro: Int,
    var titulo: String,
    var autor: String,
    var genero: String,
    var dataLancamento: LocalDate,
    var sinopse: String,
    var adaptacao: Boolean
){

}
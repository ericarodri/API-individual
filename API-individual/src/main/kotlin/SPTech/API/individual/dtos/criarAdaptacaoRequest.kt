package SPTech.API.individual.dtos

import java.time.LocalDate

data class criarAdaptacaoRequest (
    var nomeAdaptacao: String,
    var tituloLivro: String,
    var diretor:String,
    var tipoAdaptacao:String,
    var dataLancamento: LocalDate,
    var livro: Int
){
}
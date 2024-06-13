package SPTech.API.individual.dtos


data class criarResenhaRequest (
    var titulo:String? = null,
    var resenha: String? = null,
    var quantidadeEstrelas: Int? = null,
    var usuario: Int,
    var livro: Int
){
}
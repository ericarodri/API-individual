package SPTech.API.individual.Dominio


import jakarta.persistence.*

import jakarta.validation.constraints.*

@Entity
class Resenha (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idResenha: Int,
    var titulo:String,
    var resenha: String,
    var quantidadeEstrelas: Int,
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    var usuario: Usuario,
    @ManyToOne
    @JoinColumn(name = "fk_livro")
    var livro: Livro
) {
}
package SPTech.API.individual.Dominio


import jakarta.persistence.*

import jakarta.validation.constraints.*

@Entity
class Usuario (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario: Int,
    var nome: String,
    @field:Email
    var email: String,
    @field:Size(min = 6, max = 6)
    var senha: String,
    @field:Size(min = 11, max = 11)
    var telefone: String
) {
}
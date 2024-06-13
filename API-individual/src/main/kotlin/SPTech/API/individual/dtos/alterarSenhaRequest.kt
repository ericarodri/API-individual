package SPTech.API.individual.dtos

import com.fasterxml.jackson.annotation.JsonIgnore


class alterarSenhaRequest(
    var email: String,
    var senha: String,
) {
}
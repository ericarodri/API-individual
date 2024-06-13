package SPTech.API.individual.Controller

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Usuario
import SPTech.API.individual.Service.UsuarioService
import SPTech.API.individual.dtos.alterarSenhaRequest
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController (val usuarioService: UsuarioService){

    @PostMapping
    fun criarUsuario(@RequestBody @Valid novoUsuario: Usuario): ResponseEntity<Usuario> {
       return usuarioService.cadastrarUsuario(novoUsuario)
    }


    @DeleteMapping("{id}")
    fun deletarUsuario(@PathVariable id: Int): ResponseEntity<Usuario> {
        return usuarioService.removerUsuario(id)
    }

    @PatchMapping
    fun atualizarSenha(@RequestBody @Valid novaSenha: alterarSenhaRequest): ResponseEntity<Usuario>{
        return usuarioService.atualizarSenha(novaSenha)
    }

    @GetMapping
    fun listarUsuarios():ResponseEntity<List<Usuario>>{
        return usuarioService.listagem()
    }
}
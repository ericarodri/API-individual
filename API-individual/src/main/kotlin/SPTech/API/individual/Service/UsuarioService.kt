package SPTech.API.individual.Service

import SPTech.API.individual.Dominio.Adaptacao
import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Usuario
import SPTech.API.individual.Repository.UsuarioRepository
import SPTech.API.individual.dtos.alterarSenhaRequest
import org.apache.coyote.Response
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Service
class UsuarioService (val usuarioRepository: UsuarioRepository, val mapper: ModelMapper = ModelMapper()) {

    fun cadastrarUsuario(novoUsuario: Usuario): ResponseEntity<Usuario>{

        val nome = usuarioRepository.existsByNome(novoUsuario.nome)
        if(usuarioRepository.existsByEmail(novoUsuario.email)){
            throw ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe")
        } else if(nome){
            throw ResponseStatusException(HttpStatus.CONFLICT, "Esse nome de usuário já está em uso")
        }
        usuarioRepository.save(novoUsuario)
        return ResponseEntity.status(201).body(novoUsuario)
    }


    fun removerUsuario(id: Int): ResponseEntity<Usuario> {
        if(!usuarioRepository.existsById(id)){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        usuarioRepository.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    fun atualizarSenha(novaSenha:alterarSenhaRequest): ResponseEntity<Usuario>{

        val dtoSenha = mapper.map(
            novaSenha,
            Usuario::class.java
        )

        if(!usuarioRepository.existsByEmail(novaSenha.email)){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar um usuário cadastrado com esse email")
        }
        val resgatarUsuario = usuarioRepository.findByEmailLike(novaSenha.email)
        resgatarUsuario.senha = novaSenha.senha
        usuarioRepository.save(resgatarUsuario)
        return ResponseEntity.status(200).body(resgatarUsuario)
    }

    fun validarLista(lista: List<*>?){
        if(lista!!.isEmpty()){
            throw  ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum valor cadastrado")
        }
    }
    fun listagem(): ResponseEntity<List<Usuario>>{
        val lista = usuarioRepository.findAll()

        validarLista(lista)
        return ResponseEntity.status(200).body(lista)
    }


}
package SPTech.API.individual.Service

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Resenha
import SPTech.API.individual.Dominio.Usuario
import SPTech.API.individual.Repository.LivroRepository
import SPTech.API.individual.Repository.ResenhaRepository
import SPTech.API.individual.Repository.UsuarioRepository
import SPTech.API.individual.dtos.criarResenhaRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.ui.Model

@Service
class ResenhaService(
    val usuarioRepository: UsuarioRepository,
    val livroRepository: LivroRepository,
    val resenhaRepository: ResenhaRepository,
    val mapper: ModelMapper = ModelMapper()
) {

    fun criarResenha(novaResenha: criarResenhaRequest): ResponseEntity<Resenha> {

        val dtoResenha = mapper.map(
            novaResenha,
            Resenha::class.java
        )

        dtoResenha.livro = livroRepository.findById(novaResenha.livro).orElse(null)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado")

        dtoResenha.usuario = usuarioRepository.findById(novaResenha.usuario).orElse(null)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")

        resenhaRepository.save(dtoResenha)
        return ResponseEntity.status(200).body(dtoResenha)
    }

    fun removerResenha(id: Int): ResponseEntity<Void>{
        if (!resenhaRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Resenha não encontrado")
        }

        resenhaRepository.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    fun validarLista(lista: List<*>?){
        if(lista!!.isEmpty()){
            throw  ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum valor cadastrado")
        }
    }
    fun listagem(): ResponseEntity<List<Resenha>>{
        val lista = resenhaRepository.findAll()

        validarLista(lista)
        return ResponseEntity.status(200).body(lista)
    }

}
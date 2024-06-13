package SPTech.API.individual.Service

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Repository.LivroRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class LivroService (val livroRepository: LivroRepository) {


    fun adicionarLivro(novoLivro: Livro): ResponseEntity<Livro>{
        if(livroRepository.existsByTitulo(novoLivro.titulo) > 0){
            throw ResponseStatusException(HttpStatus.CONFLICT, "Livro já existe na prateleira virtual")
        }
        livroRepository.save(novoLivro)
        return ResponseEntity.status(201).body(novoLivro)
    }

    fun removerLivro(id: Int): ResponseEntity<Void> {
        if (!livroRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado")
        }

        livroRepository.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    fun validarLista(lista: List<*>?){
        if(lista!!.isEmpty()){
            throw  ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum valor cadastrado")
        }
    }
    fun listagem(): ResponseEntity<List<Livro>>{
        val lista = livroRepository.findAll()

       validarLista(lista)
        return ResponseEntity.status(200).body(lista)
    }

    fun listagemAutor(nomeAutor: String): ResponseEntity<List<Livro>>{

        val lista = livroRepository.existsByAutor(nomeAutor)

        if(lista.isEmpty()){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "O autor não possui livros cadastrados aqui")
        }

        return ResponseEntity.status(200).body(lista)

    }


}
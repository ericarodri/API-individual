package SPTech.API.individual.Service

import SPTech.API.individual.Dominio.Adaptacao
import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Resenha
import SPTech.API.individual.Dominio.Usuario
import SPTech.API.individual.Repository.AdaptacaoRepository
import SPTech.API.individual.Repository.LivroRepository
import SPTech.API.individual.dtos.criarAdaptacaoRequest
import SPTech.API.individual.dtos.criarResenhaRequest
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException


@Service
class AdaptacaoService (val adaptacaoRepository: AdaptacaoRepository, val livroRepository: LivroRepository,   val mapper: ModelMapper = ModelMapper()) {


    fun cadastrarAdaptacao(novaAdaptacao: criarAdaptacaoRequest): ResponseEntity<Adaptacao>{

        if(livroRepository.existsByTitulo(novaAdaptacao.tituloLivro) > 0){
            val verificacao = livroRepository.findByTituloLike(novaAdaptacao.tituloLivro)
            if(verificacao.adaptacao){

                val dtoAdaptacao = mapper.map(
                    novaAdaptacao,
                    Adaptacao::class.java
                )
                val verificarId = livroRepository.findById(verificacao.idLivro).get()
                dtoAdaptacao.livro = verificarId

                adaptacaoRepository.save(dtoAdaptacao)
                return ResponseEntity.status(200).body(dtoAdaptacao)
            }
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Esse livro não possui adaptação ou não existe")
    }

    fun removerAdaptacao(id: Int): ResponseEntity<Void>{
        if (!adaptacaoRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Adaptacao não encontrado")
        }

        adaptacaoRepository.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    fun validarLista(lista: List<*>?){
        if(lista!!.isEmpty()){
            throw  ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum valor cadastrado")
        }
    }
    fun listagem(): ResponseEntity<List<Adaptacao>>{
        val lista = adaptacaoRepository.findAll()

        validarLista(lista)
        return ResponseEntity.status(200).body(lista)
    }
}

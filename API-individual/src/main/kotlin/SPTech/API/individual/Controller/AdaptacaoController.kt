package SPTech.API.individual.Controller

import SPTech.API.individual.Dominio.Adaptacao
import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Service.AdaptacaoService
import SPTech.API.individual.dtos.criarAdaptacaoRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/adaptacoes")
class AdaptacaoController(val adaptacaoService: AdaptacaoService) {


    @PostMapping
    fun publicarAdaptacao(@RequestBody @Valid novaAdaptacao: criarAdaptacaoRequest): ResponseEntity<Adaptacao>{
        return adaptacaoService.cadastrarAdaptacao(novaAdaptacao)
    }

    @DeleteMapping("/{id}")
    fun deletarAdaptacao(@PathVariable id: Int): ResponseEntity<Void>{
        return adaptacaoService.removerAdaptacao(id)
    }

    @GetMapping
    fun listarAdaptacoes():ResponseEntity<List<Adaptacao>>{
        return adaptacaoService.listagem()
    }

}
package SPTech.API.individual.Controller

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Resenha
import SPTech.API.individual.Service.ResenhaService
import SPTech.API.individual.dtos.criarResenhaRequest
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/resenhas")
class ResenhaController (val resenhaService: ResenhaService){

    @PostMapping
    fun publicarResenha(@RequestBody @Valid novaResenha: criarResenhaRequest): ResponseEntity<Resenha>{
        return resenhaService.criarResenha(novaResenha)
    }

    @DeleteMapping("/{id}")
    fun deletarResenha(@PathVariable id: Int): ResponseEntity<Void>{
       return resenhaService.removerResenha(id)
    }

    @GetMapping
    fun listarResenhas():ResponseEntity<List<Resenha>>{
        return resenhaService.listagem()
    }

}
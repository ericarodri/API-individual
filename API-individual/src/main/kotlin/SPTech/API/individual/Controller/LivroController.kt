package SPTech.API.individual.Controller

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Service.LivroService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/livros")
class LivroController (val livroService: LivroService) {

    @PostMapping
    fun adicionar(@RequestBody @Valid novoLivro: Livro): ResponseEntity<Livro>{
        livroService.adicionarLivro(novoLivro)
        return ResponseEntity.status(200).body(novoLivro)
    }

    @DeleteMapping("/{id}")
    fun deletarLivro(@PathVariable id: Int): ResponseEntity<Livro> {
        livroService.removerLivro(id)
        return ResponseEntity.status(200).build()
    }

    @GetMapping("/listarLivros")
    fun listarLivros():ResponseEntity<List<Livro>>{
       return livroService.listagem()
    }

    @GetMapping("/listarAutor")
    fun listarPorAutor(@RequestParam nomeAutor: String): ResponseEntity<List<Livro>>{
        return livroService.listagemAutor(nomeAutor)
    }
}
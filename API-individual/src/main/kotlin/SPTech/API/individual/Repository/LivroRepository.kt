package SPTech.API.individual.Repository

import SPTech.API.individual.Dominio.Livro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LivroRepository: JpaRepository<Livro, Int> {

    @Query("SELECT COUNT(*) FROM Livro l WHERE l.titulo = :titulo")
    fun existsByTitulo(titulo: String): Int

    @Query("SELECT l FROM Livro l WHERE l.autor = :nomeAutor")
    fun existsByAutor(nomeAutor: String): List<Livro>


    fun findByTituloLike(tituloLivro: String): Livro


}
package SPTech.API.individual.Repository

import SPTech.API.individual.Dominio.Livro
import SPTech.API.individual.Dominio.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UsuarioRepository: JpaRepository<Usuario, Int> {


    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.email = :email")
    fun existsByEmail(email: String): Boolean

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.nome = :nome")
    fun existsByNome(nome: String): Boolean

    fun findByEmailLike(email: String): Usuario
}
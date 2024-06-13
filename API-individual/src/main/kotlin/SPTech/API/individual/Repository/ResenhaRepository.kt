package SPTech.API.individual.Repository

import SPTech.API.individual.Dominio.Resenha
import org.springframework.data.jpa.repository.JpaRepository

interface ResenhaRepository: JpaRepository<Resenha, Int> {
}
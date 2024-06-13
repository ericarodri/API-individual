package SPTech.API.individual.Repository

import SPTech.API.individual.Dominio.Adaptacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AdaptacaoRepository: JpaRepository<Adaptacao, Int> {

}
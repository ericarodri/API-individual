package SPTech.API.individual.Service

import SPTech.API.individual.Dominio.Usuario
import SPTech.API.individual.Repository.UsuarioRepository
import SPTech.API.individual.dtos.alterarSenhaRequest
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UsuarioServiceTest{

    lateinit var usuarioRepository: UsuarioRepository
    lateinit var usuarioService: UsuarioService

    @BeforeEach
    fun setUp(){
        usuarioRepository = mock(UsuarioRepository::class.java)
        usuarioService = UsuarioService(usuarioRepository)
    }

    @Test
    fun `Cadastro do usuário feito com sucesso`(){
        val novoUsuario = Usuario(
            idUsuario = 1,
            nome = "Amanda Souza",
            email = "amanda@gmail.com",
            senha = "123456",
            telefone = "12345678912")

        `when`(usuarioRepository.existsByEmail(novoUsuario.email)).thenReturn(false)
        `when`(usuarioRepository.existsByNome(novoUsuario.nome)).thenReturn(false)
        `when`(usuarioRepository.save(novoUsuario)).thenReturn(
            Usuario(
                idUsuario = 1,
                nome = "Amanda Souza",
                email ="amanda@gmail.com",
                senha = "123456",
                telefone = "12345678912"
            )
        )
        val result = usuarioService.cadastrarUsuario(novoUsuario)
        assertNotNull(result)


        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals(1, result.body?.idUsuario)
        assertEquals("Amanda Souza", result.body?.nome)
        assertEquals("amanda@gmail.com", result.body?.email)
        assertEquals("123456", result.body?.senha)
        assertEquals("12345678912", result.body?.telefone)

    }

    @Test
    fun `Cadastro dá erro pois usuário já existe, pelo email` (){
        val novoUsuario = Usuario(
            idUsuario = 1,
            nome = "Amanda Souza",
            email = "amanda@gmail.com",
            senha = "123456",
            telefone = "12345678912")

        `when`(usuarioRepository.existsByEmail(novoUsuario.email)).thenReturn(true)
        val erro = assertThrows(ResponseStatusException::class.java){
            usuarioService.cadastrarUsuario(novoUsuario)
        }

        assertEquals(HttpStatus.CONFLICT, erro.statusCode)
    }

    @Test
    fun `Deletar usuário com sucesso`(){
        val idUsuario = 1
        `when`(usuarioRepository.existsById(idUsuario)).thenReturn(true)
        val result = usuarioService.removerUsuario(idUsuario)
        assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun `Deletar usuário quando ele não existe`(){
        val idUsuario = 1
        `when` (usuarioRepository.existsById(idUsuario)).thenReturn(false)
        val erro = assertThrows(ResponseStatusException::class.java){
            usuarioService.removerUsuario(idUsuario)
        }
        assertEquals(HttpStatus.NOT_FOUND, erro.statusCode)
    }

    @Test
    fun `Atualizar senha do usuário com sucesso`() {
        val alterarSenha = alterarSenhaRequest(
            email = "amanda@gmail.com",
            senha = "000675"
        )
        val usuarioExistente = Usuario(
            idUsuario = 1,
            nome = "Amanda Souza",
            email = "amanda@gmail.com",
            senha = "123456",
            telefone = "12345678912"
        )
        `when`(usuarioRepository.existsByEmail(alterarSenha.email)).thenReturn(true)
        `when`(usuarioRepository.findByEmailLike(alterarSenha.email)).thenReturn(usuarioExistente)
        val result = usuarioService.atualizarSenha(alterarSenha)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(alterarSenha.senha, result.body?.senha)
    }

    
}
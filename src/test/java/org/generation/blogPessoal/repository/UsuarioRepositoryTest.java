package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		//Given
				usuarioRepository.save(new Usuario(0L,"João da Silva", "joao@email.com", "123456"));
				usuarioRepository.save(new Usuario(0L,"Manuela da Silva", "manuela@email.com", "123456"));
				usuarioRepository.save(new Usuario(0L,"Adriana da Silva", "adriana@email.com", "123456"));
				usuarioRepository.save(new Usuario(0L,"Paulo Antunes", "paulo@email.com", "123456"));
				}
	
	@Test
	@DisplayName("Retorna 1 Usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com"));
	}
	
	@Test
	@DisplayName("Search invalid email!")
	void searchEmailInvalidReturnOptionalEmpty() {
		
		//When
		Optional<Usuario> optional = usuarioRepository.findByUsuario("");
		
		//Then
		assertTrue(optional.isEmpty());
	}
	
	@Test
	@DisplayName("Search name Silva")
	void searchFromSilvaReturnthreeUsers() {
		
		//When
		List<Usuario> list = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		
		//Then
		assertEquals(3, list.size());
	}
	
	@Test
	@DisplayName("Search name Paulo!")
	void searchFromPauloReturnOneUser() {
		
		//When
		List<Usuario> list = usuarioRepository.findAllByNomeContainingIgnoreCase("Paulo");
		
		//Then
		assertEquals(1, list.size());
	}

	
}

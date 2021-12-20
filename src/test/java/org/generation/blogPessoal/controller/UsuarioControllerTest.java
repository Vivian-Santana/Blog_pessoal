package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UsuarioControllerTest {

	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@TestInstance(Lifecycle.PER_CLASS)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class UserControllerTest {

		@Autowired
		private TestRestTemplate testRestTemplate;

		@Autowired
		private UsuarioService usuarioService;

		//CADASTRAR USUARIO
		
		@Test
		@Order(1)
		@DisplayName("Register new User!")
		void saveNewUserReturn201() {
			// Given
			HttpEntity<Usuario> request = new HttpEntity<Usuario>(
					new Usuario(0L,"Maria Silva", "Maria@email.com", "123456"));

			// When
			ResponseEntity<Usuario> response = testRestTemplate.exchange("/usaurios/cadastrar", HttpMethod.POST,
					request, Usuario.class);

			// Then
			assertEquals(HttpStatus.CREATED, response.getStatusCode());
			assertEquals(request.getBody().getUsuario(), response.getBody().getUsuario());
			assertEquals(request.getBody().getUsuario(), response.getBody().getUsuario());

		}

		//NÃO PERMITIR USUARIO DUPLICADO
		
		@Test
		@Order(2)
		@DisplayName("Não deve permitir duplicacão do Usuário")
		public void naoDeveDuplicarUsuario() {

			usuarioService.cadastrarUsuario(new Usuario(0L,"Maria da Silva", "maria@email.com", "123456"));

			HttpEntity<Usuario> request = new HttpEntity<Usuario>(
					new Usuario(0L,"Maria da Silva", "maria@email.com", "123456"));

			ResponseEntity<Usuario> response = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request,
					Usuario.class);
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		}

		// ALTERAR UM USUARIO

		 @Test
		 @Order(3)
		  
		 @DisplayName("Alterar um Usuário!") 
		 public void deveAtualizarUmUsuario() {
		  
		 Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario
		 (0L,"Marina Sousa", "marina@email.com", "marina123"));
		  
		 Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),
		  "Marina Sousa", "Marina@email.com", "juliana123");
		 
		 HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
		 
		 ResponseEntity<Usuario> response = testRestTemplate 
				 .withBasicAuth("boaz","boaz") 
				 .exchange("/usuarios/atualizar", HttpMethod.PUT, request,Usuario.class);
		 
		 assertEquals(HttpStatus.OK, response.getStatusCode());
		 assertEquals(usuarioUpdate.getNome(), response.getBody().getNome());
		 assertEquals(usuarioUpdate.getUsuario(), response.getBody().getUsuario()); 
		 }

		// LISTAR USUARIO

		@Test
		@Order(4)
		@DisplayName("Listar todos os Usuários")

		public void deveMostrarTodosUsuarios() {

			usuarioService.cadastrarUsuario(new Usuario(0L,"Sabrina Sanches", "sabrina@email.com", "sabrina123"));

			usuarioService.cadastrarUsuario(new Usuario(0L,"Ricardo Marques", "ricardo@email.com", "ricardo123"));

			ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("boaz", "boaz").exchange("/usuarios/all",
					HttpMethod.GET, null, String.class);
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
		}

	}

}

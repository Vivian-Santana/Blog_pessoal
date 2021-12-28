package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // ANOTAÇÃO QUE INDICA QUE SE TRATA DE UMA CLASSE DE SEGURANÇA DE SPRING
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override // SOBRESCRITA DE METODO
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // THROW EXCEPTION É UMA TRATATIVA DE
																					// ERRO

		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication().withUser("boaz").password(passwordEncoder().encode("boaz"))
				.authorities("ROLE_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // METODO QUE NÃO DEVOLVE NADA, VAI ESTANCIAR UM HTTP SECURITY QUE É UM OBJ
		//CONF. QUE LIBERA END POINTS (ALGUNS CAMINHOS) DENTRO DO CONTROLLER PARA QUE O CLIENT TENHA ACESSO SEM PRECISAR PASSAR UMA CHAVE EM TOKEN
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios/logar").permitAll() //LOGAR E CADASTRAR VAI SER LIVERADO PARA O CLIENT FAZER REQUISIÇÕES DENTRO DA API
				.antMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll().anyRequest().authenticated().and() 
				.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//NÃO GUARDA SESSÃO NENHUMA
				.cors().and().csrf().disable();
	}
}

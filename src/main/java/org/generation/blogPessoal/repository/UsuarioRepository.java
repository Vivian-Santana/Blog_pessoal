package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;
import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsuario(String usuario); //ESSE USUARIO É O MESMO QUE O ATRIBUTO USUARIO USADO NA CLASSE USUARIO.JAVA //NESSE METODO ESTOU PEDINDO PARA PROCURAR PELO NOME DO USUARIO.
    //OBJETO OPTIONAL <USUARIO> É USADO POR QUE OS VALORES PODEM VIR NULOS
	
	public List <Usuario> findAllByNomeContainingIgnoreCase(String nome);
}

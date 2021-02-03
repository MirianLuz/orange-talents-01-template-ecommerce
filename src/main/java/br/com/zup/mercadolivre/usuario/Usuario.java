package br.com.zup.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank @Column(unique=true,  nullable = false)
	private String email;
	
	@NotBlank @Size(min=6)
	private String senha;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();;
	
	public Usuario(@Email @NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

}

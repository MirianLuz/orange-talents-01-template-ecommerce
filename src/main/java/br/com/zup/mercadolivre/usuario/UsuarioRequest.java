package br.com.zup.mercadolivre.usuario;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
	@Email
	@NotBlank @Column(unique=true,  nullable = false)
	private String email;
	
	@NotBlank @Size(min=6)
	private String senha;
	
	public UsuarioRequest(@Email @NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario toModel() {
		return new Usuario(email, senha);
	}
	
}

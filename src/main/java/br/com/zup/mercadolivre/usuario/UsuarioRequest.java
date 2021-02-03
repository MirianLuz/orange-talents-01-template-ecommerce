package br.com.zup.mercadolivre.usuario;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.validation.UniqueValue;

public class UsuarioRequest {
	@Email
	@NotBlank @Column(unique=true,  nullable = false)
	@UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Já existe um usuário com o email cadastrado")
	private String email;
	
	@NotBlank @Length(min=6)
	private String senha;
	
	public UsuarioRequest(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario toModel() {
		return new Usuario(email, new SenhaLimpa(senha));
	}
	
}

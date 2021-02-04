package br.com.zup.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank @Column(unique=true,  nullable = false)
	private String email;
	
	@NotBlank @Length(min=6)
	private String senha;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public Usuario() {
	}
	
	public Usuario(@Email @NotBlank String email, @NotNull @Valid SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "Email não pode ser em branco");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha limpa não pode ser nulo");
		
		this.email = email;
		this.senha = senhaLimpa.hash();
	}
	

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

}

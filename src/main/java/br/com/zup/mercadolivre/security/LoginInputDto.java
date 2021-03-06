package br.com.zup.mercadolivre.security;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	public LoginInputDto(@NotBlank String email, @NotBlank String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.email,
				this.password);
	}
}

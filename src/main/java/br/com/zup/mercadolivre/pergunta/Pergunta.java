package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	
	@ManyToOne
	@NotNull
	@Valid
	private Usuario interessado;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Deprecated
	public Pergunta() {
	}

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario interessado) {
		this.titulo = titulo;
		this.produto = produto;
		this.interessado = interessado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public Usuario getInteressado() {
		return interessado;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", produto=" + produto + ", usuario=" + interessado
				+ ", dataCriacao=" + dataCriacao + "]";
	}
	
}

package br.com.zup.mercadolivre.caracteristica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;

@Entity
public class Caracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	public Caracteristica() {
	}

	public Caracteristica(@NotBlank String nome, @NotBlank String descricao, @NotNull Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Caracteristica [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}

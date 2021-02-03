package br.com.zup.mercadolivre.subcategoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.categoria.Categoria;

@Entity
public class SubCategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Column(unique = true, nullable = false)
	private String nome;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@Deprecated
	public SubCategoria() {
	}

	public SubCategoria(@NotBlank String nome, @NotNull Categoria categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "SubCategoria [id=" + id + ", nome=" + nome + ", categoria=" + categoria + "]";
	}

}

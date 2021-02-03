package br.com.zup.mercadolivre.subcategoria;

import br.com.zup.mercadolivre.categoria.CategoriaResponse;

public class SubCategoriaResponse {

	private Long id;
	private String nome;
	private CategoriaResponse categoria;
	
	public SubCategoriaResponse(SubCategoria subCategoria) {
		this.id = subCategoria.getId();
		this.nome = subCategoria.getNome();
		this.categoria = new CategoriaResponse(subCategoria.getCategoria());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public CategoriaResponse getCategoria() {
		return categoria;
	}
	
}

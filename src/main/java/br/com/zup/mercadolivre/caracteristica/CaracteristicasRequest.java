package br.com.zup.mercadolivre.caracteristica;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.produto.Produto;

public class CaracteristicasRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	public CaracteristicasRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Caracteristica toModel(Produto produto) {
		return new Caracteristica(this.nome, this.descricao, produto);
		
	}
}

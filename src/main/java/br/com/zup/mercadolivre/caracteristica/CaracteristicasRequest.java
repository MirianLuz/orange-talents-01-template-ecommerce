package br.com.zup.mercadolivre.caracteristica;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	public Caracteristica toModel(@NotNull @Valid Produto produto) {
		return new Caracteristica(this.nome, this.descricao, produto);
		
	}

	@Override
	public String toString() {
		return "CaracteristicasRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}

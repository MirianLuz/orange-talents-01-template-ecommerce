package br.com.zup.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.validation.UniqueValue;

public class CategoriaRequest {

	@NotBlank 
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Já existe uma categoria com este nome cadastrada")
	private String nome;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public Categoria toModel() {
		return new Categoria(nome);
	}
}

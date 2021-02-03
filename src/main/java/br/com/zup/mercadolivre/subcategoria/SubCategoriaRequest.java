package br.com.zup.mercadolivre.subcategoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.validation.ExistId;
import br.com.zup.mercadolivre.validation.UniqueValue;

public class SubCategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = SubCategoria.class, fieldName = "nome")
	private String nome;
	
	@NotNull
	@ExistId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public SubCategoriaRequest(@NotBlank String nome, @NotNull Long idCategoria) {
		this.nome = nome;
		this.idCategoria = idCategoria;
	}
	
	public SubCategoria toModel(CategoriaRepository categoriaRepository) {
		@NotNull Categoria categoria = categoriaRepository.getOne(this.idCategoria);
		return new SubCategoria(nome, categoria);
	}
}

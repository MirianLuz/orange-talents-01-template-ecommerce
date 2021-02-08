package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.validation.ExistId;
import br.com.zup.mercadolivre.validation.UniqueValue;

public class ProdutoRequest {

	@NotBlank 
	@UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "Já existe um produto com este nome cadastrado")
	private String nome;
	
	@NotNull @Positive
	private BigDecimal valor;
	
	@NotNull @Positive
	private int qtdDisponivel;
	
	@NotNull @Size(min=3) @Valid
	private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();
	
	@NotBlank @Size(max = 100)
	@UniqueValue(domainClass = Produto.class, fieldName = "descricao", message = "Já existe um produto com esta descricao")
	private String descricao;
	
	@NotNull @Valid
	@ExistId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	
	
	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Positive int qtdDisponivel,
			@Valid List<CaracteristicasRequest> caracteristicas,
			@NotBlank @Size(max = 100) String descricao, @NotNull @Valid Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.caracteristicas.addAll(caracteristicas);
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public List<CaracteristicasRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicasRequest> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}


	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuarioLogado) {
		@NotNull Categoria categoria = categoriaRepository.getOne(this.idCategoria);
		return new Produto(nome, valor, qtdDisponivel, caracteristicas, descricao, categoria, usuarioLogado);
	}

	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", valor=" + valor + ", qtdDisponivel=" + qtdDisponivel
				+ ", caracteristicas=" + caracteristicas + ", descricao=" + descricao + ", idCategoria=" + idCategoria
				+ "]";
	}

}

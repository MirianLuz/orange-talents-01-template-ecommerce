package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.caracteristica.Caracteristica;
import br.com.zup.mercadolivre.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Column(unique=true, nullable = false)
	private String nome;
	
	@NotNull @Positive
	@Column(nullable = false )
	private BigDecimal valor;
	
	@NotNull @Positive
	private int qtdDisponivel;
	
	@NotNull @Size(min=3)
	@OneToMany(mappedBy = "produto")
	private List<Caracteristica> caracteristica = new ArrayList<>();
	
	@NotBlank @Size(max = 100)
	@Column(unique=true, nullable = false)
	private String descricao;
	
	@NotNull @Valid
	@ManyToOne
	private Categoria categoria;
	
	@NotNull @Valid
	@ManyToOne
	private Usuario usuarioLogado;
	
	private LocalDate dataCadastro;

	public Produto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull int qtdDisponivel,
			@NotNull @Size(min = 3) List<CaracteristicasRequest> caracteristica, @NotBlank @Size(max = 100) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario usuarioLogado) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		List<Caracteristica> novaCaracteristica = caracteristica.stream().map(c -> c.toModel(this)).collect(Collectors.toList());
		this.caracteristica = novaCaracteristica;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuarioLogado = usuarioLogado;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", qtdDisponivel=" + qtdDisponivel
				+ ", caracteristica=" + caracteristica + ", descricao=" + descricao + ", categoria=" + categoria
				+ ", usuarioLogado=" + usuarioLogado + ", dataCadastro=" + dataCadastro + "]";
	}

}

package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
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

import org.springframework.util.Assert;

import br.com.zup.mercadolivre.caracteristica.Caracteristica;
import br.com.zup.mercadolivre.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String nome;

	@NotNull
	@Positive
	@Column(nullable = false)
	private BigDecimal valor;

	@NotNull
	@Positive
	private int qtdDisponivel;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	
	@NotBlank
	@Size(max = 100)
	@Column(unique = true, nullable = false)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuarioLogado;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull BigDecimal valor, @NotNull int qtdDisponivel,
			@NotNull @Size(min = 3) Collection<CaracteristicasRequest> caracteristicas,
			@NotBlank @Size(max = 100) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario usuarioLogado) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		
		Assert.isTrue(this.caracteristicas.size()>= 3,"Todo produto precisa ter no mínimo 3 ou + características");

		this.descricao = descricao;
		this.categoria = categoria;
		this.usuarioLogado = usuarioLogado;
	}


	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", qtdDisponivel=" + qtdDisponivel
				+ ", caracteristica=" + caracteristicas + ", descricao=" + descricao + ", categoria=" + categoria
				+ ", usuarioLogado=" + usuarioLogado + ", dataCadastro=" + dataCadastro + ", imagens=" + imagens + "]";
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.usuarioLogado.equals(possivelDono);
	}

}

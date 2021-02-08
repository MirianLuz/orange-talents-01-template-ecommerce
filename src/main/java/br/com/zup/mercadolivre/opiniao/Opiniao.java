package br.com.zup.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(1)
	@Max(5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Size(max = 500)
	private String descricao;
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuarioLogado;
	
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	public Opiniao(@Size(min = 1, max = 5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Usuario usuarioLogado,
			@NotNull @Valid Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuarioLogado = usuarioLogado;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", usuarioLogado=" + usuarioLogado + ", produto=" + produto + "]";
	}
	
}

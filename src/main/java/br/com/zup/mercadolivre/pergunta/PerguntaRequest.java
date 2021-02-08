package br.com.zup.mercadolivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario interessado) {
		
		return new Pergunta(titulo, produto, interessado);
	}
}

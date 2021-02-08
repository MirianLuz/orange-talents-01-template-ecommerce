package br.com.zup.mercadolivre.pergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
public class PerguntaController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@PostMapping(value = "/produtos/{id}/pergunta")
	public String adiciona(@RequestBody @Valid PerguntaRequest request,@PathVariable("id") Long id) {
		Produto produto = produtoRepository.getOne(id);
		Usuario interessado = usuarioRepository.findByEmail("user@email.com");
		
		Pergunta pergunta = request.toModel(produto, interessado);
		
		perguntaRepository.save(pergunta);
		
		return pergunta.toString();
	}
	
}

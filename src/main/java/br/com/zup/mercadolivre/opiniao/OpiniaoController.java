package br.com.zup.mercadolivre.opiniao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.security.UsuarioLogado;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
public class OpiniaoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@PostMapping(value = "/produtos/{id}/opiniao")
	public String cria(@RequestBody @Valid OpiniaoRequest request,@PathVariable("id") Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Produto produto = produtoRepository.getOne(id);
		Usuario logado = usuarioRepository.findByEmail(usuarioLogado.getUsername());
		
		Opiniao opiniao = request.toModel(produto, logado);
		
		opiniaoRepository.save(opiniao);
		return opiniao.toString();
	}

}

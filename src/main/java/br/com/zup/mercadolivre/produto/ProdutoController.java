package br.com.zup.mercadolivre.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.security.UsuarioLogado;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public String cadastra(@RequestBody @Valid ProdutoRequest request,  @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario logado = usuarioRepository.findByEmail(usuarioLogado.getUsername());
		Produto produto = request.toModel(categoriaRepository, logado);
		produtoRepository.save(produto);
		return produto.toString();
	}
}

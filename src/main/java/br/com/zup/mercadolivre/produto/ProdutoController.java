package br.com.zup.mercadolivre.produto;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.imagem.NovasImagensRequest;
import br.com.zup.mercadolivre.imagem.Uploader;
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

	@Autowired
	private Uploader uploaderFake;
	
	@PostMapping
	public String cadastra(@RequestBody @Valid ProdutoRequest request,  @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		Usuario logado = usuarioRepository.findByEmail(usuarioLogado.getUsername());
		Produto produto = request.toModel(categoriaRepository, logado);
		produtoRepository.save(produto);
		return produto.toString();
	}
	
	@PostMapping(value = "/{id}/imagens")
	public String adcionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		/*
		 * 1) enviar imagens para o loval onde elas vão ficar
		 * 2) pegar os links de todas as imagens
		 * 3) assosciar esses links com o produto em questao
		 * 4) preciso carregar o produto
		 * 5) depois que associar eu preciso atualizar a nova versão do produto
		 */
		
		
		
		Produto produto = produtoRepository.getOne(id);
		Usuario logado = usuarioRepository.findByEmail(usuarioLogado.getUsername());
		 
		if(!produto.pertenceAoUsuario(logado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);
		
		System.out.println(links);
		produtoRepository.save(produto);
		return produto.toString();
		
		
	}
}

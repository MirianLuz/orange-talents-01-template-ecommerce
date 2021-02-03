package br.com.zup.mercadolivre.subcategoria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoriaController {

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public String cadastrar(@RequestBody @Valid SubCategoriaRequest request) {
		SubCategoria subCategoria = request.toModel(categoriaRepository);
		subCategoriaRepository.save(subCategoria);
		return subCategoria.toString();
	}
}

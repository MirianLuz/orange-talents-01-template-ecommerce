package br.com.zup.mercadolivre.subcategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubCategoriaResponseController {

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@GetMapping(value = "/subcategoria/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		SubCategoria subCategoria = subCategoriaRepository.getOne(id);
		if (subCategoria == null) {
			return ResponseEntity.notFound().build();
		}
		
		SubCategoriaResponse subCategoriaResponse = new SubCategoriaResponse(subCategoria);
		return ResponseEntity.ok(subCategoriaResponse);
		
	}
}

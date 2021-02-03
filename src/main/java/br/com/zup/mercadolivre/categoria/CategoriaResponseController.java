package br.com.zup.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaResponseController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping(value = "/categoria/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.getOne(id);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		
		CategoriaResponse categoriaResponse = new CategoriaResponse(categoria);
		return ResponseEntity.ok(categoriaResponse);
		
	}
}

package br.com.zup.mercadolivre.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/usuarios")
	public String cria(@RequestBody @Valid UsuarioRequest request) {
		
		Usuario usuario = request.toModel();
		usuarioRepository.save(usuario);
		return usuario.toString();
	}

}

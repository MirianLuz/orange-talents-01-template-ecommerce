package br.com.zup.mercadolivre.imagem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensRequest {
	
	private List<MultipartFile> imagens = new ArrayList<>();

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
	
}

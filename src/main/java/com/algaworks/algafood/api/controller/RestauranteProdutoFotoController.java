package com.algaworks.algafood.api.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	private final String PATH = "C:\\upload";
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {
	
		try {
			var nomeArquivo = UUID.randomUUID().toString()
					+"_"+ fotoProdutoInput.getArquivo().getOriginalFilename();
			var arquivoFoto = Path.of(PATH, nomeArquivo);
						Files.createDirectories(Path.of(PATH));
			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}

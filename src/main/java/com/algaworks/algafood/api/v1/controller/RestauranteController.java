
package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private CadastroRestauranteService restauranteService;	


	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler; 
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoModelAssembler
				.toCollectionModel(restauranteService.listar());
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNome() {
		return restauranteApenasNomeModelAssembler
				.toCollectionModel(restauranteService.listar());
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		return restauranteModelAssembler.toModel(restaurante);
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteModel adicionar(
			@RequestBody @Valid RestauranteInput restauranteInput){
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){

		Restaurante restauranteAtual =  restauranteService.buscarOuFalhar(restauranteId);
		restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
		try {
			return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
		 return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
		 return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		restauranteService.fechar(restauranteId);
		 return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		restauranteService.abrir(restauranteId);
		 return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/ativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("/inativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

}

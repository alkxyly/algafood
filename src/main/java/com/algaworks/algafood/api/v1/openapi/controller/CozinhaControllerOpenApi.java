package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.v1.model.CozinhaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Lista as Cozinhas")
	@GetMapping
	public PagedModel<CozinhaModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma cozinha por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel buscar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId);
	
	@ApiOperation("Cadastrauma cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrado")
	})
	public CozinhaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Atualiza uma cozinha por id")
	public CozinhaModel atualizar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId,
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Remove uma cozinha por id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha Excluida"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId);

}

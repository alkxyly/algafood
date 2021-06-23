package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	
	@ApiOperation("Lista os Grupos de Usuários")
	public List<GrupoModel> listar();
	
	@ApiOperation("Busca um grupo por id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID grupo de usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo de usuário não encontrado", response = Problem.class)
	})
	public GrupoModel buscar(
		@ApiParam(value = "ID de um grupo", example = "1", required = true)
		Long grupoId);
	
	@ApiOperation("Cadastra um grupo de usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo de usuário cadastrado")
	})
	public GrupoModel salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
			GrupoInput grupoInput);
	
	@ApiOperation("Atualiza um grupo de usuário por id")
	public GrupoModel atualizar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId,
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", 
			required = true)
			GrupoInput grupoInput);
	
	@ApiOperation("Remove um grupo de usuário por id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo de usuário Excluido"),
		@ApiResponse(code = 404, message = "Grupo de usuário não encontrado", response = Problem.class)
	})
	public void remover(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);

}

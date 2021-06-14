package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Cozinha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Lista as Cozinhas")
	@GetMapping
	public Page<CozinhaModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma cozinha por Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel buscar(Long cozinhaId);
	
	@ApiOperation("Cadastrauma cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrado")
	})
	public CozinhaModel adicionar(CozinhaInput cozinhaInput);
	
	@ApiOperation("Atualiza uma cozinha por id")
	public CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);
	
	@ApiOperation("Remove uma cozinha por id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha Excluida"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public void remover(Long cozinhaId);

}

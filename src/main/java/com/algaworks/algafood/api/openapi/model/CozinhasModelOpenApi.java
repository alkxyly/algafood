package com.algaworks.algafood.api.openapi.model;

import com.algaworks.algafood.api.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CozinhasModel")
public class CozinhasModelOpenApi extends PagedModelOpenApi<CozinhaModel> {
	
}

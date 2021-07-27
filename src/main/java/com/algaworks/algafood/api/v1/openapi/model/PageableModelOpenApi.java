package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Pageable")
public class PageableModelOpenApi {
	
	@ApiModelProperty(value = "Número da página (começa em 0)", example = "0")
	public int page;
	
	@ApiModelProperty(value = "Quantidade de elementos por página", example = "10")
	public int size;
	
	@ApiModelProperty(value = "Nome da propriedade para ordena", example = "nome,asc")
	public List<String> sort;
}

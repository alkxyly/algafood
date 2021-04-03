package com.algaworks.algafood.domain.exception;


public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	
	public CidadeNaoEncontradoException(String mensagem) {
		super( mensagem);
	}

	public CidadeNaoEncontradoException(Long estadoId) {
		this(String.format("Cidade de código %d  não encontrado", estadoId));
	}

}

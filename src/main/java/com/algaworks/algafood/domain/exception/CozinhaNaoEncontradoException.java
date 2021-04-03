package com.algaworks.algafood.domain.exception;


public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	
	public CozinhaNaoEncontradoException(String mensagem) {
		super( mensagem);
	}

	public CozinhaNaoEncontradoException(Long estadoId) {
		this(String.format("Cozinha de código %d  não encontrado", estadoId));
	}

}

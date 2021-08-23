package com.algaworks.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas{

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		public @interface PodeEditar {}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar {}
	}

	public @interface Restaurantes{

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
		public @interface PodeGerenciarCadastro {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "(hasAuthority('EDITAR_RESTAURANTES') or "
				+ "@algaSecurity.gerenciaRestaurante(#restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento { }

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar {}
	}
	
	public @interface Pedidos{

		
		@PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or"
				+ "@algaSecurity.getUsuarioId() == returnObject.cliente.id or"
				+ "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar {}
		
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and "
				+ "(hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@algaSecurity.emitiuPedido(#filtro.clienteId) or"
				+ " @algaSecurity.gerenciaRestaurante(#filtro.restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedidos {
		}
	}


}

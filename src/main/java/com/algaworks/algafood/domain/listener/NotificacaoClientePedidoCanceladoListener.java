package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		var mensagem  = Mensagem.builder()
			.assunto(event.getPedido().getRestaurante().getNome() + " - Pedido Cancelado")
			.corpo("pedido-cancelado.html")
			.variavel("pedido", event.getPedido())
			.destinatario(event.getPedido().getCliente().getEmail())
			.build();

		envioEmailService.enviar(mensagem);
	}
}

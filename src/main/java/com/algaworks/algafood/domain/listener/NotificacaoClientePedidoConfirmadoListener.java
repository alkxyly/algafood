package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		var mensagem  = Mensagem.builder()
			.assunto(event.getPedido().getRestaurante().getNome() + " - Pedido Confirmado")
			.corpo("emails/pedido-confirmado.html")
			.variavel("pedido", event.getPedido())
			.destinatario(event.getPedido().getCliente().getEmail())
			.build();

		envioEmailService.enviar(mensagem);
	}
}

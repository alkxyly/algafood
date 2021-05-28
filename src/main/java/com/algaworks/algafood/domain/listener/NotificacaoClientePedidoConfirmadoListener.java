package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmailService;
	
	@EventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		var mensagem  = Mensagem.builder()
			.assunto(event.getPedido().getRestaurante().getNome() + " - Pedido Confirmado")
			.corpo("pedido-confirmado.html")
			.variavel("pedido", event.getPedido())
			.destinatario(event.getPedido().getCliente().getEmail())
			.build();

		envioEmailService.enviar(mensagem);
	}
}

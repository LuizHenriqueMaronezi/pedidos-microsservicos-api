package io.github.luizhenriquemaronezi.pedidos_microsservicos_api.service;

import io.github.luizhenriquemaronezi.pedidos_microsservicos_api.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public Pedido enfileirarPedido(Pedido pedido){
        rabbitTemplate.convertAndSend(exchangeName, "", pedido);
        logger.info("Pedido enfileirado: {} ", pedido.toString());
        return pedido;
    }
}

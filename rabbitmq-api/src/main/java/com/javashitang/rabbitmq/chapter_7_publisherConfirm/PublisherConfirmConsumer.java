package com.javashitang.rabbitmq.chapter_7_publisherConfirm;

import com.javashitang.rabbitmq.enjoy.exchange.direct.DirectProducer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublisherConfirmConsumer {

		public static void main(String[] args) throws IOException, TimeoutException {
				ConnectionFactory factory = new ConnectionFactory();
				factory.setHost("www.javashitang.com");

				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME, "direct");

				String queueName = "focuserror";
				channel.queueDeclare(queueName, false, false, false, null);

				String bindingKey = "error";
				channel.queueBind(queueName, DirectProducer.EXCHANGE_NAME, bindingKey);

				Consumer consumer = new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag, Envelope envelope,
								AMQP.BasicProperties properties, byte[] body) throws IOException {
								String message = new String(body, "UTF-8");
								System.out.println(envelope.getRoutingKey() + " " + message);
						}
				};

				channel.basicConsume(queueName, true, consumer);
				channel.close();
				connection.close();
		}
}

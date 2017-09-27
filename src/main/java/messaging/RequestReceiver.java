package messaging;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RequestReceiver {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public RequestReceiver(ConnectionFactory connectionFactory) {
        this.factory = connectionFactory;
    }

    public void receive() throws IOException, TimeoutException {
        factory.setHost(Global.HOSTNAME);
        factory.setPort(5672);
        factory.setUsername(Global.USERNAME);
        factory.setPassword(Global.PASSWORD);
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(Global.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.");

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + msg + "'");
            }
        };
        channel.basicConsume(Global.QUEUE_NAME, true, consumer);
    }
}

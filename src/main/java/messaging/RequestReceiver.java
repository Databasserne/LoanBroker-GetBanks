package messaging;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.*;
import controllers.ClientController;
import utils.Global;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RequestReceiver {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

                JsonObject json = new JsonParser().parse(msg).getAsJsonObject();

                ClientController client = new ClientController();
                client.requestBanks(json.get("CreditScore").getAsInt(), json.get("Amount").getAsDouble(), json.get("Months").getAsInt());
            }
        };
        channel.basicConsume(Global.QUEUE_NAME, true, consumer);
    }
}

package main;

import com.rabbitmq.client.ConnectionFactory;
import messaging.RequestReceiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {

    private RequestReceiver receiver;

    public static void main(String[] args) throws IOException, TimeoutException {
        new App().start();
    }

    private void start() throws IOException, TimeoutException {
        receiver = new RequestReceiver(new ConnectionFactory());
        receiver.receive();
    }
}

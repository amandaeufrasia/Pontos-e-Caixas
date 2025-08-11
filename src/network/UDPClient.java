package network;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPClient implements ConnectionManager {
    private DatagramSocket socket;     // socket UDP para enviar ou ent receber pacotes
    private final String host;         // IP ou hostname do servidor
    private final int port;            // porta do servidor
    private InetAddress serverAddress; // endereço IP do servidor

    // construtor que recebe host e porta do servidor
    public UDPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        try {
            // cria socket UDP com porta random p/ o cliente
            socket = new DatagramSocket();
            // resolve o nome do host para IP
            serverAddress = InetAddress.getByName(host);

            System.out.println("cliente UDP criado para o servidor " + host + ":" + port);

            // pega e envia uma mensagem inicial para "registrar" o client no servidor obs: qualquer mensagem tem nada nn
            send("Euamoredes");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String msg) {
        try {
            // converte mensagem para bytes
            byte[] buf = msg.getBytes(StandardCharsets.UTF_8);

            // cria pacote UDP apontando para o servidor
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, port);

            // envia o pacote
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receive() {
        try {
            // prepara buffer p/ receber dados
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // espera mensagem do servidor (bloqueante)
            socket.receive(packet);

            // retorna mensagem recebida em texto 
            return new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // retorna null em caso de erro 
            return null;
        }
    }

    @Override
    public void close() {
        // fecha o socket caso estejja aberto
        if (socket != null && !socket.isClosed()) socket.close();
    }
}

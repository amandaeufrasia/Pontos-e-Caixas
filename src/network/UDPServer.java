package network;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPServer implements ConnectionManager {
    private DatagramSocket socket;    // socket UDP para enviar ou ent receber pacotes
    private final int port;           // porta que o servidor vai usar
    private InetAddress clientAddress; // endereço IP do cliente para responder
    private int clientPort;           // porta do cliente p/ enviar dados

    // construtor que recebe a porta p/ o servidor UDP
    public UDPServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        try {
            // cria o socket UDP na porta especificada
            socket = new DatagramSocket(port);
            System.out.println("Servidor UDP iniciado na porta " + port);

            // prepara buffer para receber o primeiro pacote do cliente p/ "conectar"
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            System.out.println("aguardando primeiro pacote do cliente para conectar...");
            socket.receive(packet); // bloqueia até receber o pacote

            // guarda endereço e porta do cliente para responder depois
            clientAddress = packet.getAddress();
            clientPort = packet.getPort();

            // mostra mensagem inicial recebida p/ ajudar no debug
            String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("cliente conectado: " + clientAddress + ":" + clientPort);
            System.out.println("mensagem inicial recebida: " + received);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String msg) {
        try {
            // converte mensagem para bytes e cria pacote UDP p/ o cliente
            byte[] buf = msg.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, clientAddress, clientPort);

            // envia o pacote para o cliente
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receive() {
        try {
            // prepara buffer para receber dados
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // espera pacote chegar (bloqueante)
            socket.receive(packet);

            // atualiza endereço e porta do cliente (caso tenha mudado)
            clientAddress = packet.getAddress();
            clientPort = packet.getPort();

            // retorna mensagem recebida como texto
            return new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // retorna null em caso de erro
            return null;
        }
    }

    @Override
    public void close() {
        // fecha o socket se estiver aberto
        if (socket != null && !socket.isClosed()) socket.close();
    }
}

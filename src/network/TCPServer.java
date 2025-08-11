package network;

import java.io.*;
import java.net.*;

public class TCPServer implements ConnectionManager {
    private ServerSocket serverSocket;  // servidor que esper conexão dos clientes
    private Socket clientSocket;        // socket para conversar com o cliente conectado
    private BufferedReader in;          // p/ ler mensagens recebidas do cliente
    private PrintWriter out;            // para enviar mensagens ao cliente
    private final int port;             // porta em que o servidor ficará escutando conexões

    // construtor que recebe a porta onde o servidor vai rodar
    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        try {
            // cria o servidor que escuta a porta especificada
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor TCP aguardando conexão na porta " + port + "...");

            // aguarda até que um cliente se conecte (bloqueia até isso acontecer
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            // prepara as streams p/ a comunicação de texto com o cliente
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true); // true para enviar direto
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String msg) {
        // envia uma linha de texto para o cliente
        out.println(msg);
    }

    @Override
    public String receive() {
        try {
            // lê uma linha de texto enviada pelo cliente (bloqueante, espera a mensagem)
            return in.readLine();
        } catch (IOException e) {
            // em caso de erro, retorna null para indicar problema
            return null;
        }
    }

    @Override
    public void close() {
        try {
            // fecha todos os recursos para liberar memória e evitar vazamenttos
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

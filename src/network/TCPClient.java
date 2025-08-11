package network;

import java.io.*;
import java.net.*;

public class TCPClient implements ConnectionManager {
    private Socket socket;               // socket p/ conectar ao servidor
    private BufferedReader in;           // para ler mensagens do servidor (in)
    private PrintWriter out;             // para enviar mensagens ao servidor (out)                 
    private final String host;           // endereço IP ou nome do servidor 
    private final int port;              // porta onde o servidor ta ouvindo

    // quem recebe host e porta para conectar
    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        try {
            // cria a conexão TCP com o servidor
            socket = new Socket(host, port);
            System.out.println("Conectado ao host " + host + ":" + port);

            // prepara os streams para comunicação de texto
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); // auto flush ativado (esvazia automaticamente o buffer)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String msg) {
        // envia mensagem p/ o servidor
        out.println(msg);
    }

    @Override
    public String receive() {
        try {
            // lê uma mensagem do servidor (bloqueante)
            return in.readLine();
        } catch (IOException e) {
            // retorna null caso dê erro
            return null;
        }
    }

    @Override
    public void close() {
        try {
            // fecha recursos e conexão
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

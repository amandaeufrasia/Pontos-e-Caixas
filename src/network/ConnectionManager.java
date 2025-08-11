package network;

// essa interface aq define o que toda conexão deve ter:
// iniciar, enviar mensagem, receber mensagem e fechar conexão.
public interface ConnectionManager extends AutoCloseable {
    void start();       // iniciar conexão (servidor ou client)
    void send(String msg);   // enviar mensagem p/ o outro lado
    String receive();        // receber mensagem do outro lado
    @Override
    void close();       // fechar conexão e liberar recursos
}

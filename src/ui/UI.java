package ui;

import game.GameLogic;
import network.*;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        // Scanner server p/ ler o que o usuário digita no console

        System.out.println("Escolha o protocolo: (1) TCP  (2) UDP");
        String protoChoice = scanner.nextLine();  
        // Lê a linha digitada pelo usuário (ex: 1 ou 2)

        System.out.println("Escolha o modo: (1) Servidor  (2) Cliente");
        String modeChoice = scanner.nextLine();

        int port = 12345; // porta padrão
        System.out.print("Digite a porta (ou ENTER para usar 12345): ");
        String portStr = scanner.nextLine();
        if (!portStr.isEmpty()) {
            try {
                port = Integer.parseInt(portStr);  // tenta converter a porta digitada p/ número
            } catch (NumberFormatException e) {
                System.out.println("Porta inválida, usando 12345.");
                port = 12345;
            }
        }

        ConnectionManager connection = null;
        boolean myTurn = false;  // indica se e a vez do jogador local
        String host = null;

        if (modeChoice.equals("1")) { // modo server
            if (protoChoice.equals("1")) {
                connection = new TCPServer(port);
            } else {
                connection = new UDPServer(port);
            }
            connection.start();
            myTurn = true; // servidor começa o jogo

            connection.send("START"); // avisa o cliente que o jogo começou

        } else { // modo cliente
            System.out.print("Digite o IP do servidor: ");
            host = scanner.nextLine();

            if (protoChoice.equals("1")) {
                connection = new TCPClient(host, port);
            } else {
                connection = new UDPClient(host, port);
            }
            connection.start();

            String startMsg = connection.receive();  // espera mensagem do servidor
            if ("START".equals(startMsg)) {
                myTurn = false;  // cliente espera o servidor jogar primeiro
            }
        }

        GameLogic game = new GameLogic(3);  // cria jogo com tabuleiro 3x3
        game.printBoard();  // mostra o tabuleiro inicial

        while (!game.isGameOver()) {
            if (myTurn) {
                System.out.println("Seu turno! Informe a jogada (ex: 0 1 H):");
                String move = scanner.nextLine();

                if (!game.applyMove(move, true)) { // tenta aplicar a jogada
                    System.out.println("Jogada inválida, tente novamente.");
                    continue;
                }

                connection.send(move);  // envia jogada p/ o adversário
                game.printBoard();
                myTurn = false;  // passa a vez
            } else {
                System.out.println("Aguardando jogada do adversário...");
                String move = connection.receive();

                if (move == null) {
                    System.out.println("Conexão perdida!");
                    break;
                }

                game.applyMove(move, false);
                System.out.println("Jogada recebida: " + move);
                game.printBoard();
                myTurn = true; // voltta a vez p/ o jogador local
            }
        }

        System.out.println("Fim de jogo! Placar final:");
        game.printScore();
        connection.close();
        scanner.close(); // fecha o scanner quando terminar
    }
}

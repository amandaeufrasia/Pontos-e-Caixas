package game;

// classe que controla a lógica do jogo "Pontos e Caixas"
public class GameLogic {
    // tamanho do tabuleiro (ex: 3 para 3x3)
    private final int size;

    // matrizes que guardam as linhas horizontais, verticais e as caixas preenchidas
    private final char[][] horizontal; 
    private final char[][] vertical;
    private final char[][] boxes;

    // pontuação dos jogadores 1 e 2
    private int scoreP1 = 0;
    private int scoreP2 = 0;

    // construtor: inicializa o jogo com o tamanho escolhido
    public GameLogic(int size) {
        this.size = size;

        // matriz p/ linhas horizontais tem uma linha a mais que o tamanho do tabuleiro
        this.horizontal = new char[size + 1][size];
        // matriz p/ linhas verticais tem uma coluna a mais que o tamanho
        this.vertical = new char[size][size + 1];
        // matriz p/ as caixas preenchidas tem o tamanho exato do tabuleiro
        this.boxes = new char[size][size];

        // inicializa todas as linhas horizontais como "vazias" (espaço em branco)
        for (int r = 0; r < size + 1; r++) {
            for (int c = 0; c < size; c++) {
                horizontal[r][c] = ' ';
            }
        }
        // inicializa todas as linhas verticais como "vazias"
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size + 1; c++) {
                vertical[r][c] = ' ';
            }
        }
        // inicializa todas as caixas como vazias
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                boxes[r][c] = ' ';
            }
        }
    }

    // método que tenta aplicar uma jogada passada como texto (ex: "0 1 H")
    // isPlayer1 indica se quem jogou foi o jogador 1 (true) ou 2 (false)
    public boolean applyMove(String move, boolean isPlayer1) {
        try {
            // divide a jogada em partes: linha, coluna e tipo (H para horizontal, V para vertical)
            String[] parts = move.trim().split(" ");
            int row = Integer.parseInt(parts[0]);    // linha escolhida
            int col = Integer.parseInt(parts[1]);    // coluna escolhida
            char type = parts[2].toUpperCase().charAt(0); // tipo de linha (H ou V)

            // se for linha horizontal
            if (type == 'H') {
                // verifica se já não existe linha marcada ali
                if (horizontal[row][col] != ' ') return false; // inválido, já marcado
                horizontal[row][col] = '-';  // marca a linha horizontal
            }
            // se for linha vertical
            else if (type == 'V') {
                // verifica se já existe linha marcada
                if (vertical[row][col] != ' ') return false; // inválido
                vertical[row][col] = '|';  // marca a linha vertical
            } else {
                return false; // tipo inválido
            }

            // depois de marcar a linha, verifica se algum quadrado foi completado
            checkBoxes(isPlayer1);

            // tudo tiver dboa na jogada. 
            return true;
        } catch (Exception e) {
            // se ocorrer erro no formato da jogada, retorna inválido
            return false;
        }
    }

    // verifica se alguma caixa foi fechada após a última jogada
    private void checkBoxes(boolean isPlayer1) {
        // percorre todas as caixas do tabuleiro
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // verifica se a caixa está vazia e as 4 linhas ao redor estão marcadas
                if (boxes[r][c] == ' ' &&             // caixa vazia
                    horizontal[r][c] != ' ' &&         // linha sup. marcada
                    horizontal[r + 1][c] != ' ' &&     // linha inf. marcada
                    vertical[r][c] != ' ' &&            // linha esq. marcada
                    vertical[r][c + 1] != ' ') {        // linha dir. marcada

                    // marca a caixa com '1' ou '2' dependendo do jogador que fechou
                    boxes[r][c] = isPlayer1 ? '1' : '2';

                    // incrementta a pontuação do jogador que fechou a caixa
                    if (isPlayer1) scoreP1++;
                    else scoreP2++;
                }
            }
        }
    }

    // verifica se o jogo acabou, ou seja, todas as caixas foram preenchidas
    public boolean isGameOver() {
        int filled = 0;
        // conta quantas caixas estão preenchidas
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (boxes[r][c] != ' ') filled++;
            }
        }
        // retorna true se todas as caixas estiverem preenchidas
        return filled == size * size;
    }

    // faz print no console o estado atual do tabuleiro
    public void printBoard() {
        for (int r = 0; r < size + 1; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print("•"); // ponto que representa o canto da caixa
                // printa linha horizontal se estiver marcada, senão espaço
                System.out.print(horizontal[r][c] == ' ' ? "   " : "---");
            }
            System.out.println("•"); // final da linha de pontos

            if (r < size) {
                for (int c = 0; c < size + 1; c++) {
                    // printa linha vertical se marcada, senão espaço
                    System.out.print(vertical[r][c] == ' ' ? "    " : "|   ");
                }
                System.out.println(); // nova linha p/ a próxima fileira do tabuleiro
            }
        }
        // printa o placar atual dos jogadores
        System.out.println("Placar: P1=" + scoreP1 + " | P2=" + scoreP2);
    }

    // printa o resultado final do jogo no console
    public void printScore() {
        System.out.println("Placar final: P1=" + scoreP1 + " | P2=" + scoreP2);
        if (scoreP1 > scoreP2) System.out.println("Jogador 1 venceu!");
        else if (scoreP2 > scoreP1) System.out.println("Jogador 2 venceu!");
        else System.out.println("Empate!");
    }
}

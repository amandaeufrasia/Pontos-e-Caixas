```markdown
# Pontos e Caixas — Multiplayer TCP/UDP (Java)

Este projeto implementa o jogo **Pontos e Caixas** (Dots and Boxes) em **Java**, com suporte a comunicação em rede usando **TCP** ou **UDP**.  
O objetivo é conectar dois jogadores em um mesmo tabuleiro, respeitando as regras do jogo, e comparar o desempenho das duas formas de comunicação.

---

## Objetivo
- Implementar o jogo **Pontos e Caixas** com regras e placar.
- Permitir a comunicação entre **cliente** e **servidor** via **TCP** ou **UDP**.
- Analisar a diferença de latência e confiabilidade entre os dois protocolos.

---

## Tecnologias Utilizadas
- **Java SE**
- **Sockets TCP e UDP**
- **Programação Orientada a Objetos**

---


## Participantes
- Anny Karine Lopes Cunha - 20241054010022
- Amanda Karoliny Eufrasia Tavares - 20241054010019
- Amanda Nogueira Epifânio - 20241054010005
- Herick Davi Azevedo Gurgel de Moraes - 20241054010045

## Estrutura do Projeto

```

- Fracionado em 3 pastas, cada uma portando de seus respectivos arquivos e funcionalidades, sendo elas:
- 1. Network: Arquivos que configuram a rede e a conexão dos clients e servers que irão operar entre si fazendo o jogo rodar em P2P.
- 2. Game: Arquivo de operação lógica do jogo, com a configuração de como funciona o jogo escolhido e como as regras dele são aplicadas.
- 3. UI: Interface e programação de exibição das posições, atualização do tabuleiro e coisas desse gênero são inseridas no arquivo UI.java dentro dessa pasta.

````

---

## Funcionalidades

- **GameLogic**  
  Controla as regras do jogo, verifica jogadas válidas, contabiliza pontos e detecta o fim da partida.

- **UI**  
  Responsável pela interação com o jogador, mostrando o tabuleiro e pedindo jogadas.

- **TCP/UDP**  
  O jogo pode ser executado tanto via TCP quanto via UDP, são os protocólos de rede.

---

## Como Executar

**Arquivo executável na pasta raiz, nomeado como "darplay.bat" compila tudo e roda sozinho.**
**OBS: Obrigatório possuir JAVA na versão 1.8.0 ou superior e JDK na versão 21.0.8 ou superior. (recomenda-se uso dessas versões para evitar bugs).**

## Como Jogar

* O tabuleiro padrão é **3x3**.
* Cada jogador escolhe uma linha (horizontal ou vertical) para marcar.
* Ao fechar uma caixa, o jogador ganha **1 ponto** e joga novamente.
* O jogo termina quando todas as caixas forem preenchidas.

---

## Possíveis Melhorias

* Implementar suporte para mais de dois jogadores.
* Adicionar interface gráfica (GUI).
* Configuração dinâmica de tamanho do tabuleiro.

```
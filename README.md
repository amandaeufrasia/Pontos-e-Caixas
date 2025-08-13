```markdown
# Pontos e Caixas — Multiplayer TCP/UDP (Java)

Este projeto implementa o jogo **Pontos e Caixas** (Dots and Boxes) em **Java**, com suporte a comunicação em rede usando **TCP** ou **UDP**.  
O objetivo é conectar dois jogadores em um mesmo tabuleiro, respeitando as regras do jogo, e comparar o desempenho das duas formas de comunicação.

---

## 🎯 Objetivo
- Implementar o jogo **Pontos e Caixas** com regras e placar.
- Permitir a comunicação entre **cliente** e **servidor** via **TCP** ou **UDP**.
- Analisar a diferença de latência e confiabilidade entre os dois protocolos.

---

## 🛠 Tecnologias Utilizadas
- **Java SE**
- **Sockets TCP e UDP**
- **Programação Orientada a Objetos**

---

## 📂 Estrutura do Projeto

```

src/
├── network/
│    ├── ConnectionManager.java   # interface para gerenciar as conexões
│    ├── TCPServer.java           # servidor TCP
│    ├── TCPClient.java           # cliente TCP
│    ├── UDPServer.java           # servidor UDP
│    ├── UDPClient.java           # cliente UDP
│
├── game/ 
│    ├── GameLogic.java                # lógica do jogo que escolhemos (Pontos e Caixas)
├── UI/ 
│    ├── UI.java                # interface em CLI do jogo


````

---

## 🔍 Funcionalidades

- **GameLogic**  
  Controla as regras do jogo, verifica jogadas válidas, contabiliza pontos e detecta o fim da partida.

- **UI**  
  Responsável pela interação com o jogador, mostrando o tabuleiro e pedindo jogadas.

- **TCP/UDP**  
  O jogo pode ser executado tanto via TCP quanto via UDP, são os protocólos de rede.

---

## 🚀 Como Executar

**Arquivo executável na pasta raiz, nomeado como "darplay.bat" compila tudo e roda sozinho.**

## 🎮 Como Jogar

* O tabuleiro padrão é **3x3**.
* Cada jogador escolhe uma linha (horizontal ou vertical) para marcar.
* Ao fechar uma caixa, o jogador ganha **1 ponto** e joga novamente.
* O jogo termina quando todas as caixas forem preenchidas.

---

## 📈 Possíveis Melhorias

* Implementar suporte para mais de dois jogadores.
* Adicionar interface gráfica (GUI).
* Configuração dinâmica de tamanho do tabuleiro.

```
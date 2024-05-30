# Hunt the Wumpus

## Descrição
Este projeto implementa o jogo "Hunt the Wumpus" em Java. O jogo consiste em um labirinto de cavernas conectadas, onde o jogador deve encontrar e derrotar o monstro Wumpus.

## Regras do Jogo
- O jogador começa em uma caverna aleatória.
- O jogador pode se mover entre as cavernas conectadas.
- O objetivo é encontrar o Wumpus e matá-lo com uma flecha.
- Existem outros perigos no labirinto: poços sem fundo e morcegos.
- O jogador pode encontrar flechas adicionais no labirinto.

## Estrutura de Dados
- `Caverna`: Representa cada caverna, com conexões para outras cavernas.
- `Direcao`: Enumeração para as direções (NORTE, SUL, LESTE, OESTE).
- `Inimigo`: Classe base para inimigos (Monstro, Morcego, Poço).
- `Player`: Representa o jogador.
- `Output`: Métodos estáticos para imprimir informações na tela.
- `Input`: Métodos estáticos para ler entrada do usuário.
- `Jogo`: Lógica principal do jogo.
- `Menu`: Menu de opções do jogo.
- `Main`: Ponto de entrada do programa.

## Como Jogar
1. Clone o repositório.
2. Compile o código.
3. Execute a classe `Main` para iniciar o jogo.

## Decisões de Implementação
- Conexões entre cavernas são geradas aleatoriamente.
- A posição inicial do jogador, inimigos e flechas são definidas aleatoriamente.

<div align="center">

# 🃏 Triple Triad

**Jogo de cartas estratégico para dois jogadores, direto no terminal**

</div>

<div align="center">

![Java: 8](https://img.shields.io/badge/Java-8-orange)
![Maven](https://img.shields.io/badge/Maven-build-blue)
![JLine](https://img.shields.io/badge/JLine-terminal-purple)
![Status: em desenvolvimento](https://img.shields.io/badge/Status-em%20desenvolvimento-yellow)

</div>

## 📘 Descrição do projeto

Este projeto é uma versão em Java do Triple Triad, o jogo de cartas conhecido do universo Final Fantasy, adaptada para disciplina. Dois jogadores se enfrentam em um tabuleiro 3x3 no terminal, jogando cartas com números nos quatro lados e tentando capturar as cartas do adversário.

A interface é feita inteiramente em texto: o tabuleiro, as cartas, a mão dos jogadores e o placar são desenhados com caracteres Unicode e cores ANSI, usando a biblioteca **JLine**.

# 🎮 Regras do jogo

- O tabuleiro é uma grade de 3 por 3 (9 casas).
- Cada carta possui 4 números, um em cada lado (Cima, Baixo, Esquerda e Direita), de 1 a 9. A letra `A` representa o maior valor possível (equivalente a 10).
- Um jogador coloca uma carta por turno em uma casa vazia. Se o número da sua carta voltado para a carta do oponente for maior que o número adjacente da carta dele, a carta é capturada e muda para a sua cor.
- O jogo termina quando as 9 casas forem preenchidas. Vence quem tiver mais cartas sob seu controle.

## Regras adicionais

- **Three Open**: três cartas de cada jogador são visíveis para o adversário.
- **Sudden Death**: o jogo termina quando um jogador vence; não são permitidos empates.
- **Order**: as cartas só podem ser jogadas na ordem em que aparecem na mão.
- **Combo**: quando um jogador captura uma ou mais cartas, as cartas capturadas podem, por sua vez, capturar cartas adjacentes, gerando uma reação em cadeia.

# 🔨 Funcionalidades do projeto

- `Sorteio de cartas`: a cada partida, 10 cartas são sorteadas de um arquivo CSV (`cards.csv`) e distribuídas, 5 para cada jogador.
- `Tabuleiro colorido`: cada carta é desenhada com bordas e os quatro valores, na cor do jogador que a controla. Quando uma carta é capturada, a cor muda.
- `Cartas visíveis do oponente`: ao lado do tabuleiro aparecem 3 cartas sorteadas da mão do adversário, cada uma com o seu índice na mão.
- `Mão do jogador`: as cartas do jogador da vez aparecem abaixo do tabuleiro.
- `Placar visual`: uma coluna com 10 quadrados coloridos mostra a divisão de pontos entre os jogadores em tempo real.
- `Combos em cadeia`: capturas se propagam para as cartas vizinhas.
- `Morte súbita`: em caso de empate, uma nova rodada é jogada até haver um vencedor.
- `Validação de jogadas`: posições fora do tabuleiro ou já ocupadas são recusadas, e o jogador pode tentar de novo.

# 🧱 Estrutura do projeto

O código é organizado em camadas, separando a regra do jogo da interface:

```
src/main/java/principal
├── Main.java            # ponto de entrada
├── console/             # interface com o usuário (entrada e saída no terminal)
├── controller/          # ponte entre a interface e o modelo
└── model/               # regras do jogo
    ├── Card.java        # carta (valores, dono, desenho)
    ├── Board.java       # tabuleiro, capturas e combos
    ├── Player.java      # jogador (cor, pontos e mão)
    ├── TripleTriad.java # lógica principal da partida
    └── enums/Color.java # cores dos jogadores

src/main/resources/data/cards.csv   # base de dados das cartas
```

# 📁 Acesso ao projeto

Clone o repositório:

```bash
git clone <link-do-repositório>
cd TripleTriad
```

# 🛠️ Abrir e rodar o projeto

**Pré-requisitos:** Java 8 ou superior e Maven.

Compile e execute pelo terminal:

```bash
mvn compile exec:java -Dexec.mainClass="principal.Main"
```

Também é possível abrir o projeto no VS Code ou no Eclipse e executar a classe `Main`.

> **Importante:** rode em um terminal de verdade (Windows Terminal, PowerShell, cmd, VS Code ou terminal do Linux/macOS). O console do Eclipse não interpreta as cores ANSI nem a limpeza de tela, e os caracteres das cartas podem aparecer errados. O projeto usa caracteres Unicode, então configure o encoding UTF-8 no compilador e, no Windows, use `chcp 65001` se os símbolos aparecerem quebrados.

## Como jogar

1. Leia as regras e pressione **ENTER** para começar.
2. A cada turno, a tela mostra o tabuleiro, as cartas visíveis do oponente, o placar e a sua mão.
3. Informe a **linha** e a **coluna** (de 0 a 2) onde quer jogar a primeira carta da sua mão.
4. Os jogadores se alternam até as 9 casas serem preenchidas.
5. O vencedor e o placar final são exibidos no fim da partida.

# ✅ Tecnologias utilizadas

- **Java 8** – Linguagem principal do projeto
- **Maven** – Gerenciamento de dependências e build
- **JLine** – Estilos de cor, limpeza de tela e manipulação do terminal

## ⚙️ Ferramentas de Desenvolvimento

- **Eclipse / VS Code** – Ambientes de desenvolvimento
- **Git e GitHub** – Controle de versão

## Autor

| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/154551754?v=4" width=115><br><sub>Carlos Henrique</sub>](https://github.com/Kafkarlos) |  [<img loading="lazy" src="https://avatars.githubusercontent.com/u/211078180?v=4" width=115><br><sub>Miguel Luan</sub>](https://github.com/MiguelLuan) | 
| :---: | :---: | 

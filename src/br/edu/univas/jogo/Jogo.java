package br.edu.univas.jogo;

import java.util.*;
import br.edu.univas.cavern.*;
import br.edu.univas.player.*;
import br.edu.univas.direction.*;
import br.edu.univas.inimigos.*;
import br.edu.univas.input.*;
import br.edu.univas.output.*;

import java.util.*;

public class Jogo {
    private List<Caverna> cavernas;
    private Player player;
    private Random random;

    public Jogo(int numeroCavernas) {
        cavernas = new ArrayList<>(numeroCavernas);
        random = new Random();
        inicializarCavernas(numeroCavernas);
        posicionarElementos();
    }

    private void inicializarCavernas(int numeroCavernas) {
        for (int i = 0; i < numeroCavernas; i++) {
            cavernas.add(new Caverna());
        }

        for (Caverna caverna : cavernas) {
            conectarCavernasAleatoriamente(caverna);
        }
    }

    private void conectarCavernasAleatoriamente(Caverna caverna) {
        List<Direcao> direcoes = Arrays.asList(Direcao.values());
        Collections.shuffle(direcoes);
        for (Direcao direcao : direcoes) {
            Caverna adjacente = cavernas.get(random.nextInt(cavernas.size()));
            if (adjacente != caverna && caverna.getCaverna(direcao) == null) {
                caverna.conectarCaverna(direcao, adjacente);
            }
        }
    }

    private void posicionarElementos() {
        // Lista de cavernas disponíveis para evitar duplicação de elementos
        List<Caverna> cavernasDisponiveis = new ArrayList<>(cavernas);
        Collections.shuffle(cavernasDisponiveis);

        // Posicionar o jogador
        Caverna cavernaInicial = cavernasDisponiveis.remove(0);
        player = new Player(cavernaInicial);

        // Posicionar Wumpus
        Caverna cavernaMonstro = cavernasDisponiveis.remove(0);
        cavernaMonstro.setInimigo(new Monstro());
        adicionarEfeitosAdjacentes(cavernaMonstro, "Você sente um odor horrível.");

        // Posicionar morcegos
        int numMorcegos = 2; // Pode ser ajustado
        for (int i = 0; i < numMorcegos; i++) {
            Caverna cavernaMorcego = cavernasDisponiveis.remove(0);
            cavernaMorcego.setInimigo(new Morcego());
            adicionarEfeitosAdjacentes(cavernaMorcego, "Você ouve o bater de asas.");
        }

        // Posicionar poços
        int numPocos = 2; // Pode ser ajustado
        for (int i = 0; i < numPocos; i++) {
            Caverna cavernaPoco = cavernasDisponiveis.remove(0);
            cavernaPoco.setInimigo(new Poco());
            adicionarEfeitosAdjacentes(cavernaPoco, "Você sente uma brisa.");
        }

        // Posicionar flechas
        int numFlechas = 3; // Pode ser ajustado
        for (int i = 0; i < numFlechas; i++) {
            Caverna cavernaFlecha = cavernasDisponiveis.remove(0);
            cavernaFlecha.setHasFlecha(true);
        }
    }

    private void adicionarEfeitosAdjacentes(Caverna caverna, String mensagem) {
        for (Caverna adjacente : caverna.getConexoes().values()) {
            adjacente.setMensagemAdjacente(mensagem);
        }
    }

    public void jogar() {
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            Output.imprimirEstadoDoJogo(player);
            System.out.println("Escolha uma ação: mover, atirar");

            String opcao = Input.lerOpcao();
            switch (opcao) {
                case "mover":
                    Direcao direcao = Input.lerDirecao();
                    moverPlayer(direcao);
                    break;
                case "atirar":
                    atirarFlecha();
                    break;
                default:
                    Output.imprimirMensagem("Opção inválida.");
            }

            if (verificarFimDeJogo()) {
                jogoAtivo = false;
            }
        }
    }

    private void moverPlayer(Direcao direcao) {
        Caverna proximaCaverna = player.getCavernaAtual().getCaverna(direcao);
        if (proximaCaverna != null) {
            player.setCavernaAtual(proximaCaverna);
            if (proximaCaverna.getInimigo() != null) {
                resolverEncontroComInimigo(proximaCaverna.getInimigo());
            }
            if (proximaCaverna.hasFlecha()) {
                player.addFlecha();
                proximaCaverna.setHasFlecha(false);
            }
        } else {
            Output.imprimirMensagem("Não há caverna nessa direção.");
        }
    }

    private void atirarFlecha() {
        if (player.getFlechas() > 0) {
            Direcao direcao = Input.lerDirecao();
            Caverna cavernaAlvo = player.getCavernaAtual().getCaverna(direcao);
            if (cavernaAlvo != null && cavernaAlvo.getInimigo() instanceof Monstro) {
                Output.imprimirMensagem("Você matou o Wumpus!");
                System.exit(0);
            } else {
                player.usarFlecha();
                Output.imprimirMensagem("Você errou.");
            }
        } else {
            Output.imprimirMensagem("Você não tem flechas.");
        }
    }

    private void resolverEncontroComInimigo(Inimigo inimigo) {
        if (inimigo instanceof Monstro) {
            Output.imprimirMensagem("O Wumpus te pegou! Você perdeu.");
            System.exit(0);
        } else if (inimigo instanceof Morcego) {
            Output.imprimirMensagem("Um morcego te pegou e te levou para outra caverna!");
            player.setCavernaAtual(cavernas.get(random.nextInt(cavernas.size())));
        } else if (inimigo instanceof Poco) {
            Output.imprimirMensagem("Você caiu em um poço!");
            player.reduzirVida(50);
            if (player.getVida() <= 0) {
                Output.imprimirMensagem("Você perdeu toda a sua vida.");
                System.exit(0);
            }
        }
    }

    private boolean verificarFimDeJogo() {
        if (player.getVida() <= 0) {
            Output.imprimirMensagem("Você perdeu toda a sua vida.");
            return true;
        }
        if (player.getFlechas() == 0) {
            boolean flechasNoMapa = cavernas.stream().anyMatch(Caverna::hasFlecha);
            if (!flechasNoMapa) {
                Output.imprimirMensagem("Você ficou sem flechas e não há mais no mapa.");
                return true;
            }
        }
        return false;
    }
}

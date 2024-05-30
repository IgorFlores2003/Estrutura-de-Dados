package br.edu.univas.menu;

import br.edu.univas.jogo.*;

public class Menu {
    public static void exibirMenu() {
        System.out.println("Bem-vindo ao jogo Hunt the Wumpus!");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("1. Iniciar jogo");
        System.out.println("2. Sair");
    }

    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                Jogo jogo = new Jogo(20);
                jogo.jogar();
                break;
            case 2:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

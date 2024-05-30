package br.edu.univas.output;
import java.util.Map;

import br.edu.univas.cavern.*;
import br.edu.univas.player.*;
import br.edu.univas.direction.*;


public class Output {
    public static void imprimirEstadoDoJogo(Player player) {
        Caverna cavernaAtual = player.getCavernaAtual();
        System.out.println("Você está na caverna: " + cavernaAtual);
        for (Map.Entry<Direcao, Caverna> entry : cavernaAtual.getConexoes().entrySet()) {
            Direcao direcao = entry.getKey();
            Caverna caverna = entry.getValue();
            System.out.println("Para " + direcao + " há uma caverna.\n");
            if (caverna.getMensagemAdjacente() != null) {
                System.out.println(caverna.getMensagemAdjacente()+"\n");
            }
        }
        System.out.println("Flechas disponíveis: " + player.getFlechas() +"\n");
        System.out.println("Vida: " + player.getVida() +"\n");
    }

    public static void imprimirMensagem(String mensagem) {
        System.out.println(mensagem +"\n");
    }
}

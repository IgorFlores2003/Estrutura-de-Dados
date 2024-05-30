package br.edu.univas.player;
import br.edu.univas.cavern.*;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Caverna cavernaAtual;
    private int flechas;
    private int vida;
    private Set<Caverna> cavernasVisitadas;

    public Player(Caverna cavernaInicial) {
        this.cavernaAtual = cavernaInicial;
        this.flechas = 3;
        this.vida = 100;
        this.cavernasVisitadas = new HashSet<>();
        this.cavernasVisitadas.add(cavernaInicial);
    }

    public Caverna getCavernaAtual() {
        return cavernaAtual;
    }

    public void setCavernaAtual(Caverna caverna) {
        this.cavernaAtual = caverna;
        this.cavernasVisitadas.add(caverna);
    }

    public int getFlechas() {
        return flechas;
    }

    public void addFlecha() {
        this.flechas++;
    }

    public void usarFlecha() {
        if (flechas > 0) {
            flechas--;
        }
    }

    public int getVida() {
        return vida;
    }

    public void reduzirVida(int quantidade) {
        vida -= quantidade;
    }

    public Set<Caverna> getCavernasVisitadas() {
        return cavernasVisitadas;
    }
}

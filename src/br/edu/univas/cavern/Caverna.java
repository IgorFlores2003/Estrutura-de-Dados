package br.edu.univas.cavern;
import br.edu.univas.direction.*;
import br.edu.univas.inimigos.*;

import java.util.HashMap;
import java.util.Map;

public class Caverna {
    private Map<Direcao, Caverna> conexoes;
    private Inimigo inimigo;
    private boolean hasFlecha;
    private String mensagemAdjacente;

    public Caverna() {
        this.conexoes = new HashMap<>();
    }

    public void conectarCaverna(Direcao direcao, Caverna caverna) {
        conexoes.put(direcao, caverna);
        caverna.conexoes.put(direcao.opposite(), this);
    }

    public Caverna getCaverna(Direcao direcao) {
        return conexoes.get(direcao);
    }

    public Map<Direcao, Caverna> getConexoes() {
        return conexoes;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public void setInimigo(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public boolean hasFlecha() {
        return hasFlecha;
    }

    public void setHasFlecha(boolean hasFlecha) {
        this.hasFlecha = hasFlecha;
    }

    public String getMensagemAdjacente() {
        return mensagemAdjacente;
    }

    public void setMensagemAdjacente(String mensagemAdjacente) {
        this.mensagemAdjacente = mensagemAdjacente;
    }
}

package br.edu.univas.wumpus;
import br.edu.univas.menu.*;
import br.edu.univas.input.*;

public class Main {
    public static void main(String[] args) {
        Menu.exibirMenu();
        int opcao = Integer.parseInt(Input.lerOpcao());
        Menu.executarOpcao(opcao);
    }
}


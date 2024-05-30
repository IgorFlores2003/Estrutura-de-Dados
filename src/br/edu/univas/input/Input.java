package br.edu.univas.input;

import java.util.Scanner;
import br.edu.univas.direction.*;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerOpcao() {
        return scanner.nextLine();
    }

    public static Direcao lerDirecao() {
        System.out.println("Escolha uma direção: LESTE, OESTE, NORTE, SUL");
        String direcao = scanner.nextLine().toUpperCase();
        return Direcao.valueOf(direcao);
    }
}




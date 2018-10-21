package Testes;

import Dominio.Entidades.Cep;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Cep(29161699).getEnderecoPorCep().toString());
    }
}

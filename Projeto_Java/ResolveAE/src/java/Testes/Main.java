package Testes;

import Dominio.Entidades.Cep;
import Infraestrutura.Util.Persistencia;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        Cep cep = new Cep(29161699);
        System.out.println(cep.getEnderecoPorCep());

    }
}

import Dominio.Entidades.Cep;
import Dominio.Entidades.Endereco;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        long startTime = System.currentTimeMillis();

        Endereco endereco = Cep.getEnderecoPorCep(29161699);
        System.out.println(endereco);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.printf("\nTEMPO de EXECUÇÃO:\t %.6f segundos", elapsedTime / 1000.00);


        startTime = System.currentTimeMillis();

        Endereco enderecoCache = Cep.getEnderecoPorCep(29161699);
        System.out.println(enderecoCache);

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.printf("\nTEMPO de EXECUÇÃO:\t %.6f segundos", elapsedTime / 1000.00);
    }

}

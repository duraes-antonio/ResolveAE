import Dominio.Entidades.Cep;
import Dominio.Entidades.Endereco;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Endereço novo, NÃO está em CACHE
        Cronometro.start();

        Endereco endereco = Cep.getEnderecoPorCep(Integer.parseInt("01001001"));
        System.out.println(endereco);

        Cronometro.stop();

        //Endereço já em CACHE
        Cronometro.start();

        Endereco enderecoCache = Cep.getEnderecoPorCep(Integer.parseInt("01001001"));
        System.out.println(enderecoCache);

        Cronometro.stop();
    }

}

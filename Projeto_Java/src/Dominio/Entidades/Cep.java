package Dominio.Entidades;

import Dominio.Util.FlyweightCep;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 20161BSI0314
 */
public class Cep implements Cloneable {

    private static final FlyweightCep cacheEnd = new FlyweightCep();

    private static int valorCep;

    public Cep() {
    }

    public Cep(int cep) {
        this.setCep(cep);
    }

    public int getCep() {
        return valorCep;
    }

    public void setCep(int valorCep) {

        if (this.validar(valorCep)) this.valorCep = valorCep;

        else throw new ValueException("Intervalo inválido para CEP.");
    }

    private boolean validar(int cep) {
        return cep >= 1000000 && cep <= 99999999;
    }

    public static Endereco getEnderecoPorCep(int cep)
            throws IllegalArgumentException, IOException {

        Endereco endereco = cacheEnd.getEndereco(cep);
        BufferedReader br;

        if (endereco == null) {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");

            try {

                URLConnection urlConexao = url.openConnection();
                InputStream is = urlConexao.getInputStream();

                //Crie um leitor p/ receber o resultado da requisição do viacep;
                br = new BufferedReader(new InputStreamReader(is));
            }

            catch (IOException ioe) {
                throw new IllegalArgumentException("Falha ao tentar obter o endereço. Reveja o CEP e tente novamente.");
            }

            StringBuilder jsonSB = new StringBuilder();

            //Armazene as linhas recebidas no String Builder;
            br.lines().forEach(l -> jsonSB.append(l.trim()));

            JSONObject jsonObj = new JSONObject(jsonSB.toString());

            if(jsonObj.toString().contains("erro")) {
                throw new IllegalArgumentException("O CEP buscado não foi encontrado!");
            }

            else {
                endereco = new Endereco(jsonObj.getString("bairro"), jsonObj.getString("localidade"), jsonObj.getString("uf"), cep, 0);
            }

            cacheEnd.addEndereco(cep, endereco);
        }

        return endereco;
    }

    @Override
    public Cep clone()
            throws CloneNotSupportedException {

        return (Cep) super.clone();
    }
}

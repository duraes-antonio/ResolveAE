package Dominio.Util;

import Dominio.Entidades.Endereco;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FlyweightCep {

    LinkedHashMap<Integer, Endereco> hashCepEnd = new LinkedHashMap<>();

    public void addEndereco(Integer cep, Endereco endereco) {

        //Se houver mais que 2 mil endereços em cache;
        //Remova os primeiros 250;
        if (hashCepEnd.size() > 2000) {
            List keys = new ArrayList<>(hashCepEnd.keySet());

            for (int i = 0; i < 250; i++) {
                hashCepEnd.remove(keys.get(i));
            }
        }

        this.hashCepEnd.put(cep, endereco);
    }

    public Endereco getEndereco(Integer cep) {

        Endereco endereco = hashCepEnd.get(cep);

        try {
            return endereco != null ? endereco.clone() : null;
        }

        catch (CloneNotSupportedException ex) {
            System.err.println("O objeto 'endereco' não é clonável!");
            return null;
        }
    }
}

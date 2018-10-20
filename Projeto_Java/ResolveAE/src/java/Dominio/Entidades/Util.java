package Dominio.Entidades;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Util {

    /**
     * Verifica se a string não é nula e contém algum char diferente de espaçamento.
     * @param texto String a ser verificada;
     * @return True se a string conter algum char. que não seja espaçamento, False,
     * se a string for nula ou apenas conter espaçamento (\s, \t, \n);
     */
    public static boolean isStringValida(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static void throwExceptCampoVazio(String nomeCampo) {
        throw new ValueException(
                String.format("O campo '%s' não pode ser vazio!", nomeCampo));
    }
}

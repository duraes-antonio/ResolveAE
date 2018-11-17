package Dominio.Entidades;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import javax.naming.SizeLimitExceededException;

/**
 * Classe responsável por verificações gerais e levantamento de exceções
 * (para padronizar o conteúdo das mensagens).
 */
public class Util {

    /**
     * Verifica se a string não é nula e contém algum char diferente de espaçamento.
     *
     * @param texto String a ser verificada;
     * @return True se a string conter algum char. que não seja espaçamento, False,
     * se a string for nula ou apenas conter espaçamento (\s, \t, \n);
     */
    public static boolean isStringValida(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Verifica se todos caracteres de uma String são dígitos;
     *
     * @param digitos String com os caracteres a serem verificados.
     * @return True se todos caracteres forem dígitos, senão, False.
     */
    public static boolean isNumero(String digitos) {

        if (digitos == null) return false;

        for (int i = 0; i < digitos.length(); i++) {
            if (!Character.isDigit(digitos.charAt(i))) return false;
        }

        return true;
    }

    /**
     * Levanta uma exceção indicando que o campo deve conter algum caractere que não seja espaço;
     *
     * @param nomeCampo Nome do campo que foi preenchido.
     * @throws ValueException
     */
    public static void throwExceptCampoVazio(String nomeCampo)
            throws ValueException {
        throw new ValueException(
                String.format("O campo '%s' não pode ser vazio!", nomeCampo));
    }


    /**
     * Dispara uma exceção indicando que o tamanho do campo é inválido.
     *
     * @param tipoChar  Deve ser 'letras', 'dígitos' ou 'caracteres'.
     * @param nomeCampo Nome do campo que foi preenchido.
     * @throws NumberFormatException
     */
    public static void throwExceptQtdInvalida(String tipoChar, String nomeCampo)
            throws NumberFormatException {
        throw new NumberFormatException(
                "Quantidade de " + tipoChar + " incorreta para o campo " + nomeCampo + ""
        );
    }

    /**
     * Dispara uma exceção indicando que o tamanho do campo é maior que o permitido.
     *
     * @param qtdMax    Número máximo aceitável de caracteres.
     * @param nomeCampo Nome do campo que foi preenchido.
     * @throws SizeLimitExceededException
     */
    public static void throwExceptQtdSupChar(String nomeCampo, int qtdMax)
            throws SizeLimitExceededException {
        throw new SizeLimitExceededException(
                "O campo '" + nomeCampo + "' deve ter no máximo " + qtdMax + " caracteres."
        );
    }

    /**
     * Dispara uma exceção indicando que o tamanho do campo é menor que o permitido.
     *
     * @param qtdMin    Número mínimo aceitável de caracteres.
     * @param nomeCampo Nome do campo que foi preenchido.
     * @throws IllegalArgumentException
     */
    public static void throwExceptQtdInfChar(String nomeCampo, int qtdMin)
            throws IllegalArgumentException {
        throw new IllegalArgumentException(
                "O campo '" + nomeCampo + "' deve ter no mínimo " + qtdMin + " caracteres."
        );
    }

    /**
     * Levanta uma exceção indicando que o campo deve conter apenas dígitos;
     *
     * @param nomeCampo Nome do campo que foi preenchido.
     * @throws ValueException
     */
    public static void throwExceptCampoNumerico(String nomeCampo)
            throws ValueException {
        throw new ValueException(
                String.format("O campo '%s' deve conter apenas dígitos de 0 a 9!", nomeCampo));
    }

    /**
     * Levanta uma exceção indicando que o campo não possui o valor mínimo aceitável;
     *
     * @param nomeCampo Nome do campo que foi preenchido.
     * @param valorMin  Valor inteiro mínimo aceitável.
     * @throws ValueException
     */
    public static void throwExceptNumeroInferior(String nomeCampo, int valorMin)
            throws ValueException {
        throw new ValueException(
                "O campo '" + nomeCampo + "' deve conter valor maior ou igual a " + valorMin + "'!"
        );
    }

    /**
     * Levanta uma exceção indicando que o campo não possui o valor mínimo aceitável;
     *
     * @param nomeCampo Nome do campo que foi preenchido.
     * @param valorMin  Valor flutuante mínimo aceitável.
     * @throws ValueException
     */
    public static void throwExceptNumeroInferior(String nomeCampo, double valorMin)
            throws ValueException {
        throw new ValueException(
                "O campo '" + nomeCampo + "' deve conter valor maior ou igual a " + valorMin + "'!"
        );
    }
}

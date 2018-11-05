package Infraestrutura.Postgre.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe que permite a criação de comandos SQL manuais.
 * Reduz a chance de erro de digitação e facilita correção quando houver algum.
 */
public class SQLProdutor {

    private ArrayList<String> strings;

    public SQLProdutor() {
        this.strings = new ArrayList<String>();
    }

    private String concatSequencia(List<String> argumentos) {
        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        argumentos.forEach(stringJoinerTemp::add);
        return stringJoinerTemp.toString();
    }

    private String concatSequencia(String... nomeColunas) {
        StringJoiner stringJoinerTemp = new StringJoiner(",");
        for (String nome : nomeColunas) stringJoinerTemp.add("\n\t" + nome);
        return stringJoinerTemp.toString();
    }

    public SQLProdutor selectAll() {
        this.strings.add("SELECT *");
        return this;
    }

    public SQLProdutor select() {
        this.strings.add("SELECT");
        return this;
    }

    public SQLProdutor select(List<String> nomeColunas) {
        this.strings.add("SELECT");
        this.strings.add(this.concatSequencia(nomeColunas));
        return this;
    }

    public SQLProdutor select(String... nomeColunas) {
        this.strings.add("SELECT");
        this.strings.add(concatSequencia(nomeColunas));
        return this;
    }

    /**
     * @param fonte Nome da fonte de dados (tabela, view ou procedure);
     * @return SQLProdutor com "FROM nome_da_fonte"
     */
    public SQLProdutor from(String fonte) {
        this.strings.add("\nFROM");
        this.strings.add(fonte);
        return this;
    }

    public SQLProdutor insert(String nomeTabela, List<String> nomeColunas) {
        this.strings.add("INSERT INTO");
        this.strings.add(nomeTabela);
        this.strings.add(
                String.join("(", this.concatSequencia(nomeColunas), ")"));
        this.strings.add("VALUES");
        return this;
    }

    public SQLProdutor update(String nomeTabela, List<String> nomeColunas) {

        this.strings.add("UPDATE");
        this.strings.add(nomeTabela);
        this.strings.add("SET");

        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        nomeColunas.forEach(x -> stringJoinerTemp.add(x + " = ?"));
        this.strings.add(stringJoinerTemp.toString());

        return this;
    }

    public SQLProdutor delete() {
        this.strings.add("DELETE *");
        return this;
    }

    public SQLProdutor values(List<String> nomeColunas) {
        List<String> argsParam = new ArrayList<>();

        //Para cada nome de coluna adicione uma interrogação p/ receber o param;
        nomeColunas.forEach(coluna -> argsParam.add("?"));

        this.strings.add("(" + this.concatSequencia(argsParam) + ")");

        return this;
    }

    public SQLProdutor where(String nomeColuna) {
        this.strings.add("\nWHERE");
        this.strings.add(nomeColuna);
        return this;
    }

    private SQLProdutor addCondicao(String operacao) {

        if (operacao.equals("=")) this.strings.add("= ?");
        else if (operacao.equals(">")) this.strings.add("> ?");
        else if (operacao.equals("<")) this.strings.add("< ?");
        else if (operacao.equals(">=")) this.strings.add(">= ?");
        else if (operacao.equals("<=")) this.strings.add("<= ?");
        else if (operacao.equals("!=")) this.strings.add("!= ?");
        else if (operacao.equals("like")) this.strings.add("LIKE ?");
        else if (operacao.equals("ilike")) this.strings.add("ILIKE ?");

        return this;
    }

    public SQLProdutor eq () {
        return this.addCondicao("=");
    }

    public SQLProdutor neq () {
        return this.addCondicao("!=");
    }

    public SQLProdutor less () {
        return this.addCondicao("<");
    }

    public SQLProdutor leq () {
        return this.addCondicao("<=");
    }

    public SQLProdutor grt () {
        return this.addCondicao("<");
    }

    public SQLProdutor grteq () {
        return this.addCondicao("<=");
    }

    public SQLProdutor in (String nomeColuna) {
        this.strings.add(nomeColuna);
        this.strings.add("IN ?");
        return this;
    }

    public SQLProdutor like () {
        return this.addCondicao("like");
    }

    public SQLProdutor ilike () {
        return this.addCondicao("ilike");
    }

    public SQLProdutor and () {
        this.strings.add("AND");
        return this;
    }

    public SQLProdutor and (String nomeColuna) {
        this.strings.add("AND");
        this.strings.add(nomeColuna);
        return this;
    }

    public SQLProdutor or () {
        this.strings.add("OR");
        return this;
    }

    public SQLProdutor or (String nomeColuna) {
        this.strings.add("OR");
        this.strings.add(nomeColuna);
        return this;
    }

    public SQLProdutor innerJoin(String nomeSegundaTabela) {
        this.strings.add("\n\tINNER JOIN");
        this.strings.add(nomeSegundaTabela);
        return this;
    }

    public SQLProdutor on (String nomeCampo1, String nomeCampo2) {
        this.strings.add("ON");
        this.strings.add(nomeCampo1);
        this.strings.add("=");
        this.strings.add(nomeCampo2);
        return this;
    }

    public SQLProdutor function(String nomeFuncao, int qtdParametros) {

        this.strings.add(nomeFuncao);
        List<String> argsParam = new ArrayList<>();

        //Para cada nome de coluna adicione uma interrogação p/ receber o param;
        for (int i = 0; i < qtdParametros; i++) {
            argsParam.add("?");
        }

        this.strings.add("(" + concatSequencia(argsParam) + ")");

        return this;
    }

    @Override
    public String toString() {
        String sql = String.join(" ", this.strings);
        this.strings.clear();
        return sql.replace(" \n", "\n") + ";";
    }

    public String pop() {
        String sql = String.join(" ", this.strings);
        this.strings.clear();
        return sql;
    }
}

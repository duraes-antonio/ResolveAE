package Infraestrutura.UtilPostgres;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe que permite a criação de comandos SQL manuais.
 * Reduz a chance de erro de digitação e facilita correção quando houver algum.
 */
public class SQLProdutor {

    private StringJoiner stringJoiner;

    public SQLProdutor() {
        this.stringJoiner = new StringJoiner(" ");
    }

    private String concatSequencia(List<String> argumentos) {
        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        argumentos.forEach(stringJoinerTemp::add);
        return stringJoinerTemp.toString();
    }

    private String concatCondicoes(List<String> nomeColunas) {
        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        nomeColunas.forEach(stringJoinerTemp::add);
        return stringJoinerTemp.toString();
    }
    
    public SQLProdutor selectAll() {
        this.stringJoiner.add("SELECT *");
        return this;
    }

    public SQLProdutor select() {
        this.stringJoiner.add("SELECT");
        return this;
    }

    public SQLProdutor select(List<String> nomeColunas) {
        this.stringJoiner.add("SELECT");
        this.stringJoiner.add(this.concatSequencia(nomeColunas));
        return this;
    }

    public SQLProdutor from(String nomeTabelaOuFuncao) {
        this.stringJoiner.add("FROM");
        this.stringJoiner.add(nomeTabelaOuFuncao);
        return this;
    }

    public SQLProdutor insert(String nomeTabela, List<String> nomeColunas) {
        this.stringJoiner.add("INSERT INTO");
        this.stringJoiner.add(nomeTabela);
        this.stringJoiner.add("(" + this.concatSequencia(nomeColunas) + ")");
        this.stringJoiner.add("VALUES");
        return this;
    }

    public SQLProdutor update(String nomeTabela, List<String> nomeColunas) {

        this.stringJoiner.add("UPDATE");
        this.stringJoiner.add(nomeTabela);
        this.stringJoiner.add("SET");

        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        nomeColunas.forEach(x -> stringJoinerTemp.add(x + " = ?"));
        this.stringJoiner.add(stringJoinerTemp.toString());

        return this;
    }

    public SQLProdutor delete() {
        this.stringJoiner.add("DELETE *");
        return this;
    }

    public SQLProdutor values(List<String> nomeColunas) {
        List<String> argsParam = new ArrayList<>();

        //Para cada nome de coluna adicione uma interrogação p/ receber o param;
        nomeColunas.forEach(coluna -> argsParam.add("?"));

        this.stringJoiner.add("(" + this.concatSequencia(argsParam) + ")");

        return this;
    }

    public SQLProdutor where(String nomeColuna) {
        this.stringJoiner.add("WHERE");
        this.stringJoiner.add(nomeColuna);
        return this;
    }

    private SQLProdutor addCondicao(String operacao) {

        if (operacao.equals("=")) this.stringJoiner.add("= ?");
        else if (operacao.equals(">")) this.stringJoiner.add("> ?");
        else if (operacao.equals("<")) this.stringJoiner.add("< ?");
        else if (operacao.equals(">=")) this.stringJoiner.add(">= ?");
        else if (operacao.equals("<=")) this.stringJoiner.add("<= ?");
        else if (operacao.equals("!=")) this.stringJoiner.add("!= ?");
        else if (operacao.equals("like")) this.stringJoiner.add("LIKE ?");
        else if (operacao.equals("ilike")) this.stringJoiner.add("ILIKE ?");

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
        this.stringJoiner.add(nomeColuna);
        this.stringJoiner.add("IN ?");
        return this;
    }

    public SQLProdutor like () {
        return this.addCondicao("like");
    }

    public SQLProdutor ilike () {
        return this.addCondicao("ilike");
    }

    public SQLProdutor and () {
        this.stringJoiner.add("AND");
        return this;
    }

    public SQLProdutor and (String nomeColuna) {
        this.stringJoiner.add("AND");
        this.stringJoiner.add(nomeColuna);
        return this;
    }

    public SQLProdutor or () {
        this.stringJoiner.add("OR");
        return this;
    }

    public SQLProdutor or (String nomeColuna) {
        this.stringJoiner.add("OR");
        this.stringJoiner.add(nomeColuna);
        return this;
    }

    public SQLProdutor innerJoin(String nomeSegundaTabela) {
        this.stringJoiner.add("INNER JOIN");
        this.stringJoiner.add(nomeSegundaTabela);
        return this;
    }

    public SQLProdutor on (String nomeCampo1, String nomeCampo2) {
        this.stringJoiner.add("ON");
        this.stringJoiner.add(nomeCampo1);
        this.eq();
        this.stringJoiner.add(nomeCampo2);
        return this;
    }

    public SQLProdutor function(String nomeFuncao, int qtdParametros) {
        
        this.stringJoiner.add(nomeFuncao).add("(");
        List<String> argsParam = new ArrayList<>();

        //Para cada nome de coluna adicione uma interrogação p/ receber o param;
        for (int i = 0; i < qtdParametros; i++) {
            argsParam.add("?");
        }
        
        this.stringJoiner.add(concatSequencia(argsParam)).add(")");

        return this;
    }

    @Override
    public String toString() {
        return this.stringJoiner.toString() + ";";
    }
}

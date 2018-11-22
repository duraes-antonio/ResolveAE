package Infraestrutura.Postgre.Util;

import Infraestrutura.Enum.ETab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Classe que permite a criação de comandos SQL manuais.
 * Reduz a chance de erro de digitação e facilita correção quando houver algum.
 */
public class SQLProdutor {

    private String nomeTabela = null;
    private ArrayList<String> strings;
    private String colunasSelect;
    private boolean deleteInsertUpdate;
    private boolean joins;

    public SQLProdutor() {
        this.strings = new ArrayList<String>();
    }

    private String concatSequencia(List<String> argumentos) {
        StringJoiner stringJoinerTemp = new StringJoiner(", ");
        argumentos.forEach(stringJoinerTemp::add);
        return stringJoinerTemp.toString();
    }

    private String concatColunas(String... nomeColunas) {
        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (String nome : nomeColunas) {

            if (!nome.contains("AS") && !nome.contains("(")) {
                stringJoinerTemp.add("\n\t" + nome + " AS " + "\"" + nome + "\"");
            }

            else {
                stringJoinerTemp.add("\n\t" + nome);
            }
        }
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

        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (String nome : nomeColunas) {
            stringJoinerTemp.add("\n\t" + nome + " AS " + "\"" + nome + "\"");
        }

        this.strings.add(stringJoinerTemp.toString());
        colunasSelect = stringJoinerTemp.toString();

        return this;
    }

    public SQLProdutor select(String... nomeColunas) {
        String colunasConcat = this.concatColunas(nomeColunas);
        this.strings.add("SELECT");
        this.strings.add(colunasConcat);

        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (String nome : nomeColunas) {

            if (!nome.contains("(")) {
                stringJoinerTemp.add(nome);
            }
        }

        colunasSelect = stringJoinerTemp.toString();
        return this;
    }

    /**
     * @param fonte Nome da fonte de dados (tabela, view ou procedure);
     * @return SQLProdutor com "FROM nome_da_fonte"
     */
    public SQLProdutor from(String fonte) {
        this.strings.add("\nFROM");
        this.strings.add(fonte);
        this.nomeTabela = fonte + ".";
        return this;
    }

    public SQLProdutor insert(ETab nomeTabela, List<String> nomeColunas) {
        this.strings.add("INSERT INTO");
        this.strings.add(nomeTabela.get());
        this.nomeTabela = nomeTabela.get() + ".";

        this.strings.add("(");
        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (String nome : nomeColunas) {
            stringJoinerTemp.add("\n\t" + nome.replace(this.nomeTabela, ""));
        }

        this.strings.add(stringJoinerTemp.toString());
        this.strings.add(")");

        this.strings.add("VALUES");
        this.strings.add("(" + concatQuestion(nomeColunas.size()) + ")");
        return this;
    }

    public SQLProdutor update(ETab nomeTabela) {

        this.strings.add("UPDATE");
        this.strings.add(nomeTabela.get());
        this.deleteInsertUpdate = true;
        this.nomeTabela = nomeTabela.get() + ".";
        return this;
    }

    public SQLProdutor set(List<String> nomeColunas) {

        this.strings.add("SET");

        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (String nome : nomeColunas) {
            stringJoinerTemp.add("\n\t" + nome.replace(nomeTabela, "") + " = ?");
        }
        this.strings.add(stringJoinerTemp.toString());

        return this;
    }

    public SQLProdutor set(String... nomeColunas) {
        return set(Arrays.stream(nomeColunas).collect(Collectors.toList()));
    }

    public SQLProdutor delete() {
        this.strings.add("DELETE");
        this.deleteInsertUpdate = true;
        return this;
    }

    private String concatQuestion(int quantidade) {
        List<String> argsParam = new ArrayList<>();

        //Para cada nome de coluna adicione uma interrogação p/ receber o param;
        for (int i = 0; i < quantidade; i++) argsParam.add("?");

        return concatSequencia(argsParam);
    }

    public SQLProdutor where(String nomeColuna) {

        this.strings.add("\nWHERE");

        if (!deleteInsertUpdate && joins) this.strings.add( nomeColuna);
        else this.strings.add("\"" + nomeColuna.replace(nomeTabela, "") + "\"");

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

    /**
     * Cláusula '=' (Igual).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor eq() {
        return this.addCondicao("=");
    }

    /**
     * Cláusula '!=' (Diferente).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor neq() {
        return this.addCondicao("!=");
    }

    /**
     * Cláusula '<' (Menor).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor less() {
        return this.addCondicao("<");
    }

    /**
     * Cláusula '<=' (Menor ou Igual).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor leq() {
        return this.addCondicao("<=");
    }

    /**
     * Cláusula '>' (Maior).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor grt() {
        return this.addCondicao(">");
    }

    /**
     * Cláusula '>=' (Maior ou Igual).
     *
     * @return instância atual do produtor de SQL.
     */
    public SQLProdutor grteq() {
        return this.addCondicao(">=");
    }

    public SQLProdutor in(String nomeColuna) {
        this.strings.add(nomeColuna);
        this.strings.add("IN ?");
        return this;
    }

    public SQLProdutor like() {
        return this.addCondicao("like");
    }

    public SQLProdutor ilike() {
        return this.addCondicao("ilike");
    }

    public SQLProdutor and() {
        this.strings.add("AND");
        return this;
    }

    public SQLProdutor and(String nomeColuna) {
        this.strings.add("AND");
        this.strings.add(nomeColuna);
        return this;
    }

    public SQLProdutor or() {
        this.strings.add("OR");
        return this;
    }

    public SQLProdutor or(String nomeColuna) {
        this.strings.add("OR");
        this.strings.add(nomeColuna);
        return this;
    }

    public SQLProdutor innerJoin(String nomeSegundaTabela) {
        this.strings.add("\n\tINNER JOIN");
        this.strings.add(nomeSegundaTabela);
        joins = true;
        return this;
    }

    public SQLProdutor on(String nomeCampo1, String nomeCampo2) {
        this.strings.add("ON");
        this.strings.add(nomeCampo1);
        this.strings.add("=");
        this.strings.add(nomeCampo2);
        return this;
    }

    public SQLProdutor limit(Integer valor) {

        if (valor != null && valor > 0) {
            this.strings.add("LIMIT");
            this.strings.add(String.valueOf(valor));
        }
        return this;
    }

    public SQLProdutor offset(Integer valor) {

        if (valor != null && valor > 0) {
            this.strings.add("OFFSET");
            this.strings.add(String.valueOf(valor));
        }
        return this;
    }

    public SQLProdutor orderBy(String... ordemColunas) {
        this.strings.add("ORDER BY");

        StringJoiner stringJoinerTemp = new StringJoiner(",");

        Arrays.stream(ordemColunas).forEach(stringJoinerTemp::add);

        this.strings.add(stringJoinerTemp.toString());

        return this;
    }

    public SQLProdutor orderBy(int... ordemColunas) {
        this.strings.add("ORDER BY");

        StringJoiner stringJoinerTemp = new StringJoiner(",");

        for (int ordem : ordemColunas) {
            stringJoinerTemp.add(String.valueOf(ordem));
        }

        this.strings.add(stringJoinerTemp.toString());

        return this;
    }

    public SQLProdutor desc() {
        this.strings.add("DESC");
        return this;
    }

    public SQLProdutor asc() {
        this.strings.add("ASC");
        return this;
    }

    public SQLProdutor groupBy() {
        this.strings.add("\nGROUP BY");
        this.strings.add(this.colunasSelect);
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
//        System.out.println(sql);
        this.strings.clear();
        return sql.replace(" \n", "\n");
    }
}

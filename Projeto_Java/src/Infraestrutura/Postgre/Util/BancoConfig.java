package Infraestrutura.Postgre.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BancoConfig {

    private enum ETipoEstruturaSQL {
        FUNCTION("function_"),
        INDEX("index_"),
        VIEW("view_");

        private String nome;

        ETipoEstruturaSQL(String nomeEstrutura) {
            nome = nomeEstrutura;
        }

        public String getNome() {
            return this.nome;
        }
    }

    private static Persistencia persistencia = Persistencia.get();
    private static boolean bancoPronto = false;

    private static String carregarArquivo(String path) {

        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder texto = new StringBuilder();
        String linha;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            while ((linha = br.readLine()) != null) {
                texto.append(linha);
                texto.append("\n");
            }
        }

        catch (IOException e) {
            texto = null;
        }

        finally {

            try {
                if(fr != null) fr.close();
                if(br != null) br.close();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Objects.requireNonNull(texto).toString();
    }

    private static List<String> buscarPaths(ETipoEstruturaSQL tipoEstrutura) {

        File arquivo = new File("./src/Infraestrutura/Postgre/Util/sql");
        List<String> paths = new ArrayList<>();

        for (File path: arquivo.listFiles()) {

            String pahtString = path.getPath();

            if (pahtString.contains(tipoEstrutura.getNome())) {
                paths.add(pahtString);
            }
        }

        return paths;
    }

    private static void criar(ETipoEstruturaSQL tipoEstrutura) {

        try {

            List<String> paths = buscarPaths(tipoEstrutura);

            for (String path: paths) {

                if (path.contains(tipoEstrutura.getNome())) {
                    persistencia.executar(carregarArquivo(path));
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void criarFuncoes() {
        criar(ETipoEstruturaSQL.FUNCTION);
    }

    private static void criarViews() {
        criar(ETipoEstruturaSQL.VIEW);
    }

    private static void criarIndices() {
        criar(ETipoEstruturaSQL.INDEX);
    }

    /**
     * Criar no banco as seguintes estruturas: funções, índices e visões;
     */
    public static void prepararBanco() {

        if (!bancoPronto) {
            criarIndices();
            System.out.println("->Índices finalizados!");
            criarViews();
            System.out.println("->Visões finalizadas!");
            criarFuncoes();
            System.out.println("->Funções finalizadas!");
            bancoPronto = true;
        }
    }
}

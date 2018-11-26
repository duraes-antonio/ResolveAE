package Infraestrutura.Enum;

public enum EConfigBD {

    ANTONIO("resolve_ae", "localhost", "postgres", "postgres", 5432),
    ELIMAR("resolve_ae", "localhost", "postgres", "postgres", 5433),
    IFES("resolve_ae", "localhost", "postgres", "serra", 5432);

    private String dataBase;
    private String host;
    private String usuario;
    private String senha;
    private int porta;

    EConfigBD(String dataBase, String host, String usuario, String senha, int porta) {
        this.dataBase = dataBase;
        this.host = host;
        this.usuario = usuario;
        this.senha = senha;
        this.porta = porta;
    }

    public String getUrlDriver() {
        return String.format("jdbc:postgresql://%s:%d/", host, porta);
    }

    public String getDataBase() {
        return dataBase;
    }

    public String getHost() {
        return host;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public int getPorta() {
        return porta;
    }
}

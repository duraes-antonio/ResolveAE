package Dominio.Enum;

public enum ETipoInfoProfissional {

    CURSO_TECNICO("Curso técnico"),
    DOMINIO_TECNOLOGICO("Domínio tecnológico"),
    GRADUACAO("Graduação"),
    OUTROS_CURSOS("Outros cursos"),
    POS_GRADUACAO("Pós-graduação"),
    TRABALHO("Trabalho");

    private String tipo;

    private ETipoInfoProfissional(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {

        int id = -1;

        if (this == DOMINIO_TECNOLOGICO) id = 1;
        else if (this == CURSO_TECNICO) id = 2;
        else if (this == GRADUACAO) id = 3;
        else if (this == POS_GRADUACAO) id = 4;
        else if (this == TRABALHO) id = 5;
        else if (this == OUTROS_CURSOS) id = 6;

        return id;
    }

    public static ETipoInfoProfissional getByString(String texto) {
        ETipoInfoProfissional eTipoContato = null;

        for (ETipoInfoProfissional obj: ETipoInfoProfissional.values()) {

            if (obj.tipo.equalsIgnoreCase(texto)) {
                eTipoContato = obj;
            }
        }

        return  eTipoContato;
    }

    public static ETipoInfoProfissional getById(int id) {

        ETipoInfoProfissional tipo = null;

        if (id == 1) tipo = DOMINIO_TECNOLOGICO;
        else if (id == 2) tipo = CURSO_TECNICO;
        else if (id == 3) tipo = GRADUACAO;
        else if (id == 4) tipo = POS_GRADUACAO;
        else if (id == 5) tipo = TRABALHO;
        else if (id == 6) tipo = OUTROS_CURSOS;

        return tipo;
    }
}

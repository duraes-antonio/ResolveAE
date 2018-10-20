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
}

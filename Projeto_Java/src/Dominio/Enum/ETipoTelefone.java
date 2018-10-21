package Dominio.Enum;

public enum ETipoTelefone {

    CELULAR("Celular"),
    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private String tipo;

    private ETipoTelefone(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

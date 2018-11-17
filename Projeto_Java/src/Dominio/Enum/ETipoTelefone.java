package Dominio.Enum;

public enum ETipoTelefone {

    CELULAR("Celular"),
    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private String tipo;

    private ETipoTelefone(String tipo) {
        this.tipo = tipo;
    }

    public static ETipoTelefone getByString(String texto) {
        ETipoTelefone eTipoContato = null;

        for (ETipoTelefone obj : ETipoTelefone.values()) {

            if (obj.tipo.equalsIgnoreCase(texto)) {
                eTipoContato = obj;
            }
        }

        return eTipoContato;
    }

    public String getTipo() {
        return tipo;
    }
}

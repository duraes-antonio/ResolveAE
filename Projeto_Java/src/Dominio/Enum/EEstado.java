package Dominio.Enum;

public enum EEstado {
    AC("Acre", 1),
    AL("Alagoas", 2),
    AM("Amazonas", 3),
    AP("Amapá", 4),
    BA("Bahia", 5),
    CE("Ceará", 6),
    DF("Distrito Federal", 7),
    ES("Espírito Santo", 8),
    GO("Goiás", 9),
    MA("Maranhão", 10),
    MG("Minas Gerais", 11),
    MS("Mato Grosso do Sul", 12),
    MT("Mato Grosso", 13),
    PA("Pará", 14),
    PB("Paraíba", 15),
    PE("Pernambuco", 16),
    PI("Piauí", 17),
    PR("Paraná", 18),
    RJ("Rio de Janeiro", 19),
    RN("Rio Grande do Norte", 20),
    RO("Rondônia", 21),
    RR("Roraima", 22),
    RS("Rio Grande do Sul", 23),
    SC("Santa Catarina", 24),
    SE("Sergipe", 25),
    SP("São Paulo", 26),
    TO("Tocantins", 27);

    private String estado;
    private int id;

    EEstado(String sigla, int id) {
        this.estado = sigla;
        this.id = id;
    }

    public static EEstado getByString(String texto) {
        EEstado eTipoContato = null;

        for (EEstado obj : EEstado.values()) {

            if (obj.estado.equalsIgnoreCase(texto)) {
                eTipoContato = obj;
            }
        }

        return eTipoContato;
    }

    public static EEstado getById(int id) {

        EEstado eTipoContato = null;

        for (EEstado obj : EEstado.values()) {

            if (obj.getId() == id) {
                eTipoContato = obj;
            }
        }

        return eTipoContato;
    }

    public String getNomeExtenso() {
        return estado;
    }

    public int getId() {
        return id;
    }
}

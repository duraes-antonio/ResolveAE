package Dominio.Enum;

public enum EEstado {
    AC("Acre"), AL("Alagoas"), AM("Amazonas"),
    AP("Amapá"), BA("Bahia"), CE("Ceará"),
    DF("Distrito Federal"), ES("Espírito Santo"), GO("Goiás"),
    MA("Maranhão"), MG("Minas Gerais"), MS("Mato Grosso do Sul"),
    MT("Mato Grosso"), PA("Pará"), PB("Paraíba"),
    PE("Pernambuco"), PI("Piauí"), PR("Paraná"),
    RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RO("Rondônia"),
    RR("Roraima"), RS("Rio Grande do Sul"), SC("Santa Catarina"),
    SE("Sergipe"), SP("São Paulo"), TO("Tocantins");

    private String estado;

    private EEstado(String sigla) {
        this.estado = sigla;
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

    public String getNomeExtenso() {
        return estado;
    }
}

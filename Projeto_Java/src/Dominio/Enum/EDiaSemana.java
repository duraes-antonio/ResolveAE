package Dominio.Enum;

public enum EDiaSemana {

    DOMINGO("Domingo"),
    SEGUNDA_FEIRA("Segunda-Feira"),
    TERCA_FEIRA("Terça-Feira"),
    QUARTA_FEIRA("Quarta-Feira"),
    QUINTA_FEIRA("Quinta-Feira"),
    SETXA_FEIRA("Sexta-Feira"),
    SABADO("Sábado");

    private String dia;

    private EDiaSemana(String dia) {
        this.dia = dia;
    }

    public static EDiaSemana getByString(String texto) {
        EDiaSemana eTipoContato = null;

        for (EDiaSemana obj : EDiaSemana.values()) {

            if (obj.dia.equalsIgnoreCase(texto)) {
                eTipoContato = obj;
            }
        }

        return eTipoContato;
    }

    public String getDiaExtenso() {
        return dia;
    }
}

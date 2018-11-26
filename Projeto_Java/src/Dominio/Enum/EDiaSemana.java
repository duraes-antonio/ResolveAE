package Dominio.Enum;

public enum EDiaSemana {

    DOMINGO("Domingo", 1),
    SEGUNDA_FEIRA("Segunda-Feira", 2),
    TERCA_FEIRA("Terça-Feira", 3),
    QUARTA_FEIRA("Quarta-Feira", 4),
    QUINTA_FEIRA("Quinta-Feira", 5),
    SETXA_FEIRA("Sexta-Feira", 6),
    SABADO("Sábado", 7);

    private String dia;
    private int id;

    EDiaSemana(String dia, int id) {
        this.dia = dia;
        this.id = id;
    }

    public static EDiaSemana getByString(String texto) {
        EDiaSemana eDiaSemana = null;

        for (EDiaSemana obj : EDiaSemana.values()) {

            if (obj.dia.equalsIgnoreCase(texto)) {
                eDiaSemana = obj;
            }
        }

        return eDiaSemana;
    }

    public static EDiaSemana getById(int id) {

        EDiaSemana eDiaSemana = null;

        for (EDiaSemana diaSemana : EDiaSemana.values()) {

            if (diaSemana.id == id) {
                eDiaSemana = diaSemana;
            }
        }

        return eDiaSemana;
    }

    public int getId() {
        return id;
    }

    public String getDiaExtenso() {
        return dia;
    }
}

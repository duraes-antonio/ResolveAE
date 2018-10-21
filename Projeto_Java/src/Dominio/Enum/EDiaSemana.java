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

    public String getDiaExtenso() {
        return dia;
    }
}

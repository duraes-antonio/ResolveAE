package model;

import java.text.SimpleDateFormat;

public class Evento {
    private SimpleDateFormat horaInic;
    private SimpleDateFormat horaFim;
    private Atividade atv;

    public Evento(){

    }

    public SimpleDateFormat getHoraInic() {
        return horaInic;
    }

    public void setHoraInic(SimpleDateFormat horaInic) {
        this.horaInic = horaInic;
    }

    public SimpleDateFormat getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(SimpleDateFormat horaFim) {
        this.horaFim = horaFim;
    }

    public Atividade getAtv() {
        return atv;
    }

    public void setAtv(Atividade atv) {
        this.atv = atv;
    }
}


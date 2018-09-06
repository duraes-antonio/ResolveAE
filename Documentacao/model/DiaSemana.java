package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DiaSemana {

    private SimpleDateFormat horaInic = new SimpleDateFormat("00:00");
    private SimpleDateFormat horaFim = new SimpleDateFormat("23:59");
    private ArrayList<Evento> lstEvent;

    public DiaSemana(){

    }

    public Evento getHoraLivre(int min){
        //ROTINA DE BUSCA DE HORA LIVRE
        Evento evento = new Evento();
        return evento;
    }

    public boolean addEvento(Evento evento){
        //ROTINA DE VERIFICACAO DE CONFLITO DE HORARIO
        //RETORNA FALSO CASO HAJA CONFLITO
        //CASO NAO HAJA, O EVENDO É ADICIONADO AO ARRAYLIST
        this.lstEvent.add(evento);
        return true;
        //return false;


    }

}


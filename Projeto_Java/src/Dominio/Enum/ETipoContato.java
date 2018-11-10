package Dominio.Enum;

import java.util.Arrays;
import java.util.List;

public enum ETipoContato {

    CELULAR("Celular"),
    FACEBOOK("Facebook"),
    GITHUB("Github"),
    LATTES("Lattes"),
    LINKEDIN("Linkedin"),
    SKYPE("Skype"),
    TELEFONE("Telefone"),
    TELEGRAM("Telegram"),
    TWITTER("Twitter"),
    WHATSAPP("WhatsApp");

    private String nome;
    private static List<ETipoContato> tiposContatos = Arrays.asList(
            CELULAR, FACEBOOK, GITHUB, LATTES, LINKEDIN, SKYPE, TELEFONE, TELEGRAM,
            TWITTER, WHATSAPP);

    private ETipoContato(String tipo) {
        this.nome = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        int id = tiposContatos.indexOf(this);
        return (id > -1) ? id + 1 : id;
    }

    public static int getIdTipoContato(ETipoContato tipo) {
        int id = tiposContatos.indexOf(tipo);
        return (id > -1) ? id + 1 : id;
    }

    public static ETipoContato getTipoContatoPorId(int id) {
        return id <= tiposContatos.size() ? tiposContatos.get(id - 1) : null;
    }
}

package Dominio.Enum;

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

    private ETipoContato(String tipo) {
        this.nome = tipo;
    }

    public String getNome() {
        return nome;
    }
}

package Dominio.Enum;

public enum ETipoMidiaSocial {

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

    private String tipo;

    private ETipoMidiaSocial(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

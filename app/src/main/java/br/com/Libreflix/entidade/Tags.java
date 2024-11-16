package br.com.Libreflix.entidade;

public enum Tags {
    ACAO("acao"),
    ROMANCE("romance"),
    TERROR("terror"),
    SUSPENSE("suspense"),
    COMEDIA("comedia"),
    DOCUMENTARIOS("documentarios"),
    DRAMA("drama");

    private String tag;

    Tags(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

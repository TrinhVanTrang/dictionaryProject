package jfxcode;

public class Vocabulary {
    private int id;
    private String word;
    private String html;
    private String description;
    private String pronounce;

    public Vocabulary() {
        id=0;
        word="";
        html="";
        description="";
        pronounce="";
    }

    public Vocabulary(int id, String word, String html, String description, String pronounce) {
        this.id = id;
        this.word = word;
        this.html = html;
        this.description = description;
        this.pronounce = pronounce;
    }

    public Vocabulary(int id) {
        this.id = id;
        word="";
        html="";
        description="";
        pronounce="";
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public void setAll(int id, String word, String html, String description, String pronounce) {
        this.setId(id);
        this.setWord(word);
        this.setHtml(html);
        this.setDescription(description);
        this.setPronounce(pronounce);
    }

    @Override
    public String toString() {
        return this.word;
    }
}

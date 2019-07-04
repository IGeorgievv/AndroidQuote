package georgievv.dailysmarts.Quotes.ui;


public class Quote {

    boolean isLiked;

    String quote;
    String author;
    String link;

    public Quote(String quote, String author, String link) {
        this.quote   = quote;
        this.author  = author;
        this.link    = link;
        this.isLiked = false;
    }

    public Quote(String quote, String author, String link, boolean isLiked) {
        this.quote   = quote;
        this.author  = author;
        this.link    = link;
        this.isLiked = isLiked;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}

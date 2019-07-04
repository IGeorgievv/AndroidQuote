package georgievv.dailysmarts.Data.Quote;

import georgievv.dailysmarts.Quotes.ui.Quote;

public class QuoteModel {

    String quoteText;
    String quoteAuthor;
    String quoteLink;

    public Quote getQuote() {
        return new Quote(quoteText, quoteAuthor, quoteLink);
    }
}

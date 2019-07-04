package georgievv.dailysmarts.Web.Quotes;

import georgievv.dailysmarts.Data.Quote.QuoteModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteService {

    @GET("/api/1.0/")
    Call<QuoteModel> getRandomQuote(
            @Query("method") String method,
            @Query("format") String format,
            @Query("lang") String lang
    );
}

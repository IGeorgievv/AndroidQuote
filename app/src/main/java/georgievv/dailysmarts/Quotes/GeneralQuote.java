package georgievv.dailysmarts.Quotes;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import georgievv.dailysmarts.Data.Quote.QuoteModel;
import georgievv.dailysmarts.Quotes.User.UserQuote;
import georgievv.dailysmarts.Quotes.ui.QuoteAdapter;
import georgievv.dailysmarts.R;
import georgievv.dailysmarts.Web.Quotes.QuoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GeneralQuote {

    private GeneralQuote instance;
    private Context context;


    public static GeneralQuote getInstance(Context context) {
        if(instance == null) {
            instance = new GeneralQuote(context);
        }
        return instance;
    }

    private GeneralQuote(Context context) {
        context = context;
    }


    private void setGallery() {

        mRecyclerView  = view.findViewById(R.id.rec_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new QuoteAdapter(getContext(), quotes, new CallBack() {

            @Override
            public void callback() {
                new UserQuote();
                setGallery();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void getDailyQuote() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.forismatic.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteService service = retrofit.create(QuoteService.class);
        Call<QuoteModel> currentQuote = service.getRandomQuote(
                "getQuote", "json", "en");
        currentQuote.enqueue(new Callback<QuoteModel>() {
            @Override
            public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                if(response != null && response.isSuccessful()) {
                    QuoteModel model = response.body();
                    quote = model.getQuote();
                    if ( quotes.size() >= 1 ) {

                        if ( checkQuote(quote.getQuote(), quote.getAuthor()) != null )
                            getDailyQuote();

                        quotes.clear();
                        quotes.add(quote);

                        refreshDaily.setEnabled(true);
                        refreshDaily.setVisibility(View.VISIBLE);
                    } else {
                        quotes.add(quote);
                    }
                    setGallery();
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<QuoteModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

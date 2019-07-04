package georgievv.dailysmarts.Quotes.App;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import georgievv.dailysmarts.Data.Quote.QuoteModel;
import georgievv.dailysmarts.Quotes.CallBack;
import georgievv.dailysmarts.Quotes.User.UserQuote;
import georgievv.dailysmarts.Quotes.ui.Quote;
import georgievv.dailysmarts.Quotes.ui.QuoteAdapter;
import georgievv.dailysmarts.Quotes.ui.QuoteViewHolder;
import georgievv.dailysmarts.R;
import georgievv.dailysmarts.Web.Quotes.QuoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyQuote extends Fragment {

    FrameLayout frameLayout;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    QuoteAdapter adapter;

    private View view;
    private Quote quote;

    private ArrayList<Quote> quotes = new ArrayList<Quote>();
    ImageButton refreshDaily;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_daily_quote, container, false);

        refreshDaily = (ImageButton) this.getActivity().findViewById(R.id.refresh);
        refreshDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshDaily.setEnabled(false);
                refreshDaily.setVisibility(View.INVISIBLE);
                getDailyQuote();
            }
        });
        getDailyQuote();

        return view;
    }

    private Quote checkQuote(String text, String author) {
        for(Quote quote : quotes) {
            if(quote.getQuote().equals(text) && quote.getAuthor().equals(author)) {
                return quote;
            }
        }
        return null;
    }

    private QuoteViewHolder.GalleryItemListener getClickListener() {
        return new QuoteViewHolder.GalleryItemListener() {
            @Override
            public void onGalleryItemClicked(Quote quote) {
                Toast.makeText(getActivity(), "Gallery item clicked: "+ quote.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}

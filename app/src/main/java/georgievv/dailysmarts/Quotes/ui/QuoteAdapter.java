package georgievv.dailysmarts.Quotes.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import georgievv.dailysmarts.Data.Quote.QuoteEntity;
import georgievv.dailysmarts.Data.Quote.QuoteInstance;
import georgievv.dailysmarts.Quotes.CallBack;
import georgievv.dailysmarts.Quotes.QuoteActivity;
import georgievv.dailysmarts.Quotes.User.UserQuote;
import georgievv.dailysmarts.R;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private QuoteViewHolder.GalleryItemListener clickListener;
    private UserQuote renderQuotes;

    final CallBack callback;

    private ArrayList<Quote> quotes;

    private Context context;

    public QuoteAdapter(Context context, ArrayList<Quote> quotes, CallBack callback) {
        this.callback = callback;
        this.quotes = quotes;

        this.context = context;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sample_quote, viewGroup, false);
        QuoteViewHolder holder = new QuoteViewHolder(v, clickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuoteViewHolder quoteViewHolder, int position) {
        final Quote dataQuote = quotes.get(position);
        quoteViewHolder.quote.setText(dataQuote.quote);
        quoteViewHolder.author.setText(dataQuote.author);
        if ( dataQuote.isLiked == true ) {
            quoteViewHolder.like.setImageResource(R.drawable.ic_favorite_black_24px);
        }

        quoteViewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( dataQuote.isLiked == true ) {
                    dataQuote.isLiked = false;
                    quoteViewHolder.like.setImageResource(R.drawable.ic_favorite_border_black_24px);
                    deleteQuote(dataQuote);
                    callback.callback();
                } else {
                    keepQuote(dataQuote);
                    dataQuote.isLiked = true;
                    quoteViewHolder.like.setImageResource(R.drawable.ic_favorite_black_24px);
                }
            }
        });

        quoteViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, dataQuote.quote +"\nBy "+ dataQuote.author);

                context.startActivity(Intent.createChooser(share, "Share quote"));
            }
        });
        quoteViewHolder.setQuoteBox(dataQuote);
    }

    private void keepQuote(Quote quote) {
        QuoteEntity saveQuote = new QuoteEntity();
        saveQuote.setQuote(quote.quote);
        saveQuote.setAuthor(quote.author);
        saveQuote.setLink(quote.link);

        QuoteInstance dbi = new QuoteInstance(context);
        dbi.insertSingleAsync(saveQuote);

        refreshFragment();
    }

    private void deleteQuote(Quote quote) {
        QuoteInstance dbi = new QuoteInstance(context);
        dbi.deleteSingleAsync(quote.link);
    }

    public void refreshFragment() {
//        Intent i = new Intent(context, QuoteActivity.class);
////        Intent i = new Intent(context, QuoteActivity.class);
//        i.putExtra("bla", "1");
//        context.startActivity(i);
        Fragment currentFragment = ((QuoteActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_daily_quote);
//        Fragment currentFragment = ((QuoteActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_daily_quote);
        if (currentFragment != null) {
//            if (currentFragment instanceof UserQuote) {
            FragmentTransaction fragTransaction =   (((QuoteActivity) context)).getSupportFragmentManager().beginTransaction();
            fragTransaction.detach(currentFragment);
            fragTransaction.attach(currentFragment);
            fragTransaction.commit();
        }
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}

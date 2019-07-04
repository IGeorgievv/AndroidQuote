package georgievv.dailysmarts.Quotes.User;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import georgievv.dailysmarts.Data.DatabaseInstance;
import georgievv.dailysmarts.Data.Quote.QuoteEntity;
import georgievv.dailysmarts.Data.Quote.QuoteInstance;
import georgievv.dailysmarts.Quotes.CallBack;
import georgievv.dailysmarts.Quotes.ui.Quote;
import georgievv.dailysmarts.Quotes.ui.QuoteAdapter;
import georgievv.dailysmarts.Quotes.ui.QuoteViewHolder;
import georgievv.dailysmarts.R;

public class UserQuote extends Fragment {
    FrameLayout frameLayout;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    QuoteAdapter adapter;
    private Context useContext = getContext();
    View view;

    private ArrayList<Quote> quotes = new ArrayList<Quote>();
    TabLayout tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_quote, container, false);
        useContext = getContext();

//        tabs = (TabLayout) this.getActivity().findViewById(R.id.tabLayout);
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch(tab.getPosition()) {
//                    case 0:
//                        break;
//                    case 1:
//                        renderQuotes();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });

        renderQuotes();


        return view;
    }

    public void renderRecycle() {

//        if ( quotes.size() <= 0 )
//            return;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new QuoteAdapter(useContext, quotes, new CallBack() {

            @Override
            public void callback() {
                renderQuotes();
            }
        });
        mRecyclerView.setAdapter(adapter);
        Toast.makeText(useContext, "New 2!", Toast.LENGTH_SHORT).show();
    }

    private void renderQuotes() {
//        QuoteInstance dbi = (QuoteInstance) QuoteInstance.getInstance(USE_CONTEXT);
        QuoteInstance dbi = new QuoteInstance(useContext);
        quotes.clear();

        dbi.getAll(new DatabaseInstance.DatabaseListener<List<QuoteEntity>>() {
            @Override
            public void onDataReceived(List<QuoteEntity> data) {

                for (QuoteEntity u : data) {
                    quotes.add(
                            new Quote(
                                    u.getQuote(), u.getAuthor(), u.getLink(), true
                            )
                    );
                }
                Toast.makeText(useContext, "New !", Toast.LENGTH_SHORT).show();
                renderRecycle();
            }
        });
    }

    private QuoteViewHolder.GalleryItemListener getClickListener() {
        return new QuoteViewHolder.GalleryItemListener() {
            @Override
            public void onGalleryItemClicked(Quote quote) {
//                Toast.makeText(DailyQuote.this, "Gallery item clicked: " + quote.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}

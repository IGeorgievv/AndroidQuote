package georgievv.dailysmarts.Quotes;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import georgievv.dailysmarts.Quotes.App.DailyQuote;
import georgievv.dailysmarts.Quotes.User.UserQuote;
import georgievv.dailysmarts.R;
import georgievv.dailysmarts.Tabs.TabAdapter;
import georgievv.dailysmarts.databinding.ActivityQuoteBinding;

public class QuoteActivity extends AppCompatActivity {

    private ActivityQuoteBinding binding;

    private TabAdapter adapter;
    private TabLayout  tabLayout;
    private ViewPager  viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote);

        viewPager = (ViewPager) binding.viewPager;
        tabLayout = (TabLayout) binding.tabLayout;

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new DailyQuote(), "Daily Quote");
        adapter.addFragment(new UserQuote(), "My Quote");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

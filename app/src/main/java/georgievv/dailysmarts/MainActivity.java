package georgievv.dailysmarts;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import georgievv.dailysmarts.Quotes.QuoteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent k = new Intent(MainActivity.this, QuoteActivity.class);
        startActivity(k);
    }
}

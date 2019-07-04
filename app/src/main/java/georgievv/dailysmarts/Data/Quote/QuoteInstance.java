package georgievv.dailysmarts.Data.Quote;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import georgievv.dailysmarts.Data.DatabaseInstance;

public class QuoteInstance extends DatabaseInstance {

    public QuoteInstance(Context context) {
        super(context);
        getInstance(context);
    }

    public void deleteSingleAsync(final String link) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.quoteDao().delete(db.quoteDao().findByLink(link));
                return null;
            }
        }.execute();
    }

    public void insertSingleAsync(final QuoteEntity quote) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.quoteDao().insertSingle(quote);
                return null;
            }
        }.execute();
    }

    public void getAll(final DatabaseListener<List<QuoteEntity>> callback) {
        new AsyncTask<Void, Void, List<QuoteEntity>>() {

            @Override
            protected List<QuoteEntity> doInBackground(Void... voids) {
                List<QuoteEntity> users = db.quoteDao().getAll();
                return users;
            }

            @Override
            protected void onPostExecute(List<QuoteEntity> listOfQuotes) {
                super.onPostExecute(listOfQuotes);
                callback.onDataReceived(listOfQuotes);
            }
        }.execute();
    }
}

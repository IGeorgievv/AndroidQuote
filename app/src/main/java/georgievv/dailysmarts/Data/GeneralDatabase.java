package georgievv.dailysmarts.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import georgievv.dailysmarts.Data.Quote.QuoteDao;
import georgievv.dailysmarts.Data.Quote.QuoteEntity;

@Database(entities = {QuoteEntity.class}, version = 2)
public abstract class GeneralDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();
}

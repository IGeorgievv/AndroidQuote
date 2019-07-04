package georgievv.dailysmarts.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseInstance {

    protected GeneralDatabase db;
    protected static DatabaseInstance instance;
    protected static Context context;
    protected final static String DB_NAME = "quote_app.db";
    protected final static int DB_VERSION = 2;

    public static DatabaseInstance getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseInstance(context);
        }
        return instance;
    }

    protected DatabaseInstance() {}

    protected DatabaseInstance(Context context) {
        //do nothing
        if (context == null)
            return;
        db = Room
                .databaseBuilder(context, GeneralDatabase.class, DB_NAME)
                .addMigrations(new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        //do nothing
                    }
                })
                .build();
    }

    public interface DatabaseListener<T> {
        void onDataReceived(T data);
    }
}

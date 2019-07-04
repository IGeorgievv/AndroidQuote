package georgievv.dailysmarts.Data.Quote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {

    @Query("SELECT * FROM quotes")
    List<QuoteEntity> getAll();

    @Query("SELECT * FROM quotes WHERE uid IN (:userIds)")
    List<QuoteEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM quotes WHERE link = :link")
    QuoteEntity findByLink(String link);

    @Query("SELECT * FROM quotes WHERE author LIKE :author")
    QuoteEntity findByAuthor(String author);

    @Insert
    void insertAll(QuoteEntity... quote);

    @Insert
    void insertSingle(QuoteEntity quote);

    @Delete
    void delete(QuoteEntity quote);
}

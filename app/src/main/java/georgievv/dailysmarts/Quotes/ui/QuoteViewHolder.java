package georgievv.dailysmarts.Quotes.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import georgievv.dailysmarts.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {

    TextView quote;
    TextView author;
    ImageButton like;
    ImageButton share;
    Quote quoteBox;

    public QuoteViewHolder(@NonNull View itemView, final GalleryItemListener listener) {
        super(itemView);
        quote  = itemView.findViewById(R.id.quote);
        author = itemView.findViewById(R.id.author);
        like   = itemView.findViewById(R.id.like);
        share  = itemView.findViewById(R.id.share);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onGalleryItemClicked(quoteBox);
//            }
//        });
    }

    public void setQuoteBox(Quote quoteBox) {
        this.quoteBox = quoteBox;
    }

    public interface GalleryItemListener {
        void onGalleryItemClicked(Quote quoteBox);
    }
}

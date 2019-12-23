package n.b.m.auabnb.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import n.b.m.auabnb.ApartmentActivity;
import n.b.m.auabnb.Netwrok.Upload;
import n.b.m.auabnb.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private isAnimationMoved animation;



    public ImageAdapter(Context mContext, List<Upload> mUploads, isAnimationMoved animation) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        this.animation = animation;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_image_adapter, parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.bind(uploadCurrent);
        holder.topPrice.setText(Double.toString(uploadCurrent.getPrice()));
        holder.topPartner.setText(uploadCurrent.getTitle());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .into(holder.topImage);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class  ImageViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView1;
        public ImageView topImage;
        public TextView topPartner;
        public TextView topPrice;

         public ImageViewHolder(View itemView){
            super(itemView);
            cardView1 = itemView.findViewById(R.id.top_card_apart1);
            topImage = itemView.findViewById(R.id.card_view_top);
            topPartner = itemView.findViewById(R.id.top_text);
            topPrice = itemView.findViewById(R.id.top_price);
        }

        public void bind(final Upload uploadCurrent) {
            cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ApartmentActivity.class);
                    v.getContext().startActivity(intent);
//                    animation.isOpen(uploadCurrent);
                    animation.fadeIn();
                }
            });
        }
    }


    public List<Upload> getmUploads() {
        return mUploads;
    }

    public void setmUploads(List<Upload> mUploads) {
        this.mUploads = mUploads;
    }

    public interface isAnimationMoved{
        public void fadeIn();
//        public void isOpen(Upload upload);
    }
}

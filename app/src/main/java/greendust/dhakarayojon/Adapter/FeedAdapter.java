package greendust.dhakarayojon.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import greendust.dhakarayojon.Model.Feed;
import greendust.dhakarayojon.Model.ItemModel;
import greendust.dhakarayojon.R;

/**
 * Created by Joker on 7/24/16.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private ItemModel itemModel;
    Context context;

    public FeedAdapter(ItemModel item) {
        itemModel = item;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public ImageView image;
        public TextView txtStatus;
        public ImageView proPic;
        public TextView url;
        public TextView timeStamp;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView)v.findViewById(R.id.tvData);
            image = (ImageView)v.findViewById(R.id.feedImage1);
            txtStatus = (TextView)v.findViewById(R.id.txtStatusMsg);
            proPic = (ImageView)v.findViewById(R.id.profilePic);
            url = (TextView)v.findViewById(R.id.txtUrl);
            timeStamp = (TextView)v.findViewById(R.id.timestamp);
        }
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        // set the Context here
        context = parent.getContext();
        return vh;
    }

    public Feed getItem(int position) {
        return itemModel.getFeed()[position];
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(getItem(position).getName());
        Picasso.with(holder.image.getContext()).load(getItem(position).getImage()).into(holder.image);
        holder.txtStatus.setText(getItem(position).getStatus());
        Picasso.with(holder.image.getContext()).load(getItem(position).getProfilePic()).into(holder.proPic);
        holder.url.setText(getItem(position).getUrl());
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(getItem(position).getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timeStamp.setText(timeAgo);
    }

    @Override
    public int getItemCount() {
        return itemModel.getFeed().length;
    }


}

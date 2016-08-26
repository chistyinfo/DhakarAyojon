package greendust.dhakarayojon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by chisty on 8/25/2016.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ItemModel itemModel;
    Context context;
    public MusicAdapter(ItemModel item) {
        itemModel = item;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public ImageView image;
        public TextView txtStatus,venu,cont;
        //        public ImageView proPic;
//        public TextView url;
        public TextView timed;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView)v.findViewById(R.id.tvData);
            image = (ImageView)v.findViewById(R.id.feedImage1);
            timed= (TextView) v.findViewById(R.id.timed);
            venu= (TextView) v.findViewById(R.id.venu);
            cont= (TextView) v.findViewById(R.id.cont);
            txtStatus = (TextView)v.findViewById(R.id.txtStatusMsg);
//            proPic = (ImageView)v.findViewById(R.id.profilePic);
//            url = (TextView)v.findViewById(R.id.txtUrl);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.timed.setText(getItem(position).getTimed());
        holder.venu.setText(getItem(position).getVenu());
        holder.cont.setText(getItem(position).getCont());
        Picasso.with(holder.image.getContext()).load(getItem(position).getImage()).into(holder.image);
        holder.txtStatus.setText(getItem(position).getStatus());
//        Picasso.with(holder.image.getContext()).load(getItem(position).getProfilePic()).into(holder.proPic);
//        holder.url.setText(getItem(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return itemModel.getFeed().length;
    }

    }


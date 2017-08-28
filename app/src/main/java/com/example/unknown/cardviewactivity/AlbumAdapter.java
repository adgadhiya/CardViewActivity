package com.example.unknown.cardviewactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by UNKNOWN on 7/11/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context context;
    private List<Album> albumList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title,count;
        public ImageView thumbnail,overflow;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            count = (TextView)itemView.findViewById(R.id.count);
            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
            overflow = (ImageView)itemView.findViewById(R.id.overflow);
        }
    }

    public AlbumAdapter(Context context,List<Album> albumList){
        this.context = context;
        this.albumList=albumList;
    }

    @Override
    public AlbumAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.album_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumAdapter.MyViewHolder holder, int position) {

        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText("Education");

        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenu(holder.overflow);
                    }
                }
        );
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(context,view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_album,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popupMenu.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public  MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){

                case R.id.action_add_favourite:
                    Toast.makeText(context,"Add to favourite",Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.action_play_next:
                    Toast.makeText(context,"Play next",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

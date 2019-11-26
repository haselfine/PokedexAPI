package com.example.pokedexapi.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexapi.DB.Favorite;
import com.example.pokedexapi.R;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {

    private final static String TAG = "Favorite list adapter";


    private FavoriteListener mListener;

    private List<Favorite> mFavorites;

    FavoriteListAdapter(Context context, FavoriteListener eventListener){
        this.mListener = eventListener;
    }

    void setFavorites(List<Favorite> favorites){
        this.mFavorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
        return new FavoriteViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position){
        if(mFavorites != null){
            Favorite favorite = mFavorites.get(position);
            holder.bind(favorite);
        } else {
            holder.bind(null);
        }
    }

    @Override
    public int getItemCount(){
        if(mFavorites == null){
            return 0;
        }
        return mFavorites.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private TextView favName;
        private TextView favDateAdded;
        private TextView favType;
        private Drawable favBackground;

        public FavoriteViewHolder(@NonNull View itemView){
            super(itemView);

            favName = itemView.findViewById(R.id.favorite_name_textView);
            favDateAdded = itemView.findViewById(R.id.date_created_textView);
            favType = itemView.findViewById(R.id.favorite_type_textView);
            favBackground = itemView.getResources().getDrawable(R.drawable.favorite_background);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view){
            mListener.onListClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view){
            mListener.onListLongClick(getAdapterPosition());
            return true;
        }

        void bind(Favorite favorite){
            Log.d(TAG, "binding favorite " + favorite);
            if(favorite == null){
                favName.setText("");
                favDateAdded.setText("");
                favType.setText("");
            } else {
                favName.setText(favorite.getName());
                favDateAdded.setText(favorite.getDate().toString());
                favType.setText(favorite.getType());

            }
        }
    }
}

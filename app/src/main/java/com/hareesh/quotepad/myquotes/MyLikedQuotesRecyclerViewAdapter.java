package com.hareesh.quotepad.myquotes;

/**
 * Created by Hareesh on 8/31/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hareesh.quotepad.Quote;
import com.hareesh.quotepad.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MyLikedQuotesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;
    private Context context;
    View view;
    int pos;
    RecyclerView mRecyclerView;
    RecyclerView.ViewHolder holder;
    GestureDetector gestureDetector;
    FirebaseDatabase database;
    DatabaseReference myRef, likeRef;
    Firebase likeFirebase, globalLikeFirebase;
    FirebaseUser user;
    Quote q1, q2, q3, qtemp;
    boolean quoteLiked;

    public MyLikedQuotesRecyclerViewAdapter(List<Object> contents, Context context, RecyclerView mRecyclerView) {
        this.contents = contents;
        this.context = context;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_small_myquotes, parent, false);

        holder = new RecyclerView.ViewHolder(view) {
        };

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView authorText = (TextView) holder.itemView.findViewById(R.id.authorText);
        TextView quoteText = (TextView) holder.itemView.findViewById(R.id.quoteText);
        ImageView authorImage = (ImageView) holder.itemView.findViewById(R.id.authorImageCard);

        if (position < MyQuotesActivity.myLikedQuotes.size()) {
            authorText.setText(MyQuotesActivity.myLikedQuotes.get(position).getPerson());
            quoteText.setText("".concat(MyQuotesActivity.myLikedQuotes.get(position).getQuote()));
            if(MyQuotesActivity.imageURLs.get(position).equals("noimage")){
                Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png").into(authorImage);
            }
            else{
                Picasso.with(context).load(MyQuotesActivity.imageURLs.get(position)).into(authorImage);
            }
        }
    }
}

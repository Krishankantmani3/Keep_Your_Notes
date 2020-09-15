package com.example.android.materialdesignapp;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater inflater;
    private List<Word> mAllWords;
    private ClickEventInRecylerView clickEventInRecylerView;


    WordListAdapter(Context context, ClickEventInRecylerView clickEventInRecylerView) {
        inflater=LayoutInflater.from(context);
        this.clickEventInRecylerView= clickEventInRecylerView;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.listitem,parent,false);
        return new WordViewHolder(view,clickEventInRecylerView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WordViewHolder holder, final int position) {
        if(mAllWords!=null) {
            holder.title.setText(mAllWords.get(position).getTitle().trim());
            holder.description.setText(mAllWords.get(position).getDescription().trim());

        }
        else{
            holder.title.setText("Add title");
            holder.title.setText("Add Description");
        }

    }

    void setWord(List<Word> mWord){mAllWords=mWord; notifyDataSetChanged();}



    @Override
    public int getItemCount() {
        if(mAllWords!=null)
        return mAllWords.size();
        else
            return 0;
    }

    class  WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    TextView title;
    TextView description;
    ClickEventInRecylerView clickEventInRecylerView;

    WordViewHolder(@NonNull View itemView,ClickEventInRecylerView clickEventInRecylerView) {
        super(itemView);
        itemView.setBackgroundColor(Color.parseColor("#F6EDD6"));
        title=itemView.findViewById(R.id.tvtitle);
        description=itemView.findViewById(R.id.tvdesc);
        this.clickEventInRecylerView=clickEventInRecylerView;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

        @Override
        public void onClick(View view) {
         clickEventInRecylerView.onWordClick(getAdapterPosition(),mAllWords.get(getAdapterPosition()).getId());
        }

        @Override
        public boolean onLongClick(View view) {
          clickEventInRecylerView.onWordLongClic(getAdapterPosition(),mAllWords.get(getAdapterPosition()).getId(),itemView);
        return true;
        }
    }




    interface  ClickEventInRecylerView{
        void onWordClick(int position,int id);
        void onWordLongClic(int position,int id,View view);
    }

}

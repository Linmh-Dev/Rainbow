package com.rainbow.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rainbow.MyApplication;
import com.rainbow.R;
import com.rainbow.room.Word;

public class HistoryAdapter extends PagedListAdapter<Word, HistoryAdapter.MyViewHolder> {
    protected HistoryAdapter() {
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getChinaese().equals(newItem.getChinaese())&&oldItem.getEnglish().equals(newItem.getEnglish());
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.recycleview_cell, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = getItem(position);
        holder.title.setText(String.valueOf(word.getEnglish()));
        holder.detail.setText(String.valueOf(word.getChinaese()));
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,detail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        title=itemView.findViewById(R.id.textViewtitle);
        detail=itemView.findViewById(R.id.textViewdetail);
        }
    }
}

package com.example.urbandictonaryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.model.Definition;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Definition> data;

    public SearchAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.urban_item, parent, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Definition datas = data.get(position);

        holder.tvTitle.setText(datas.getWord());
        holder.tvAuthor.setText(datas.getAuthor());
        holder.tvExample.setText(datas.getExample());
        holder.tvDefinition.setText(datas.getDefinition());
        holder.btnThumbsUp.setText(String.valueOf(datas.getThumbsUp()));
        holder.btnThumbsDown.setText(String.valueOf(datas.getThumbsDown()));
        holder.tvDate.setText(datas.getWrittenOn());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setupData(List<Definition> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDefinition;
        private TextView tvExample;
        private TextView tvAuthor;
        private MaterialButton btnThumbsUp;
        private MaterialButton btnThumbsDown;
        private TextView tvDate;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDefinition = itemView.findViewById(R.id.tvDefinition);
            tvExample = itemView.findViewById(R.id.tvExample);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            btnThumbsUp = itemView.findViewById(R.id.btnThumbsUp);
            btnThumbsDown = itemView.findViewById(R.id.btnThumbsDown);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}

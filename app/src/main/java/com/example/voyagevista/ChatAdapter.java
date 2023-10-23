package com.example.voyagevista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Mvh> {

    List<Chat> chatList;
    public ChatAdapter(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public Mvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, null);
        Mvh myViewHolder = new Mvh(chatView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Mvh holder, int position) {
        Chat chat = chatList.get(position);
        if(chat.getSentBy().equals("user")){
            holder.responsechat.setVisibility(View.GONE);
            holder.sentchat.setVisibility(View.VISIBLE);
            holder.sentchat_view.setText(chat.getChatcontent());
        }else{
            holder.sentchat.setVisibility(View.GONE);
            holder.responsechat.setVisibility(View.VISIBLE);
            holder.responsechat_view.setText(chat.getChatcontent());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class Mvh extends RecyclerView.ViewHolder{

        LinearLayout responsechat, sentchat;
        TextView responsechat_view, sentchat_view;

        public Mvh(@NonNull View itemView) {
            super(itemView);
            responsechat = itemView.findViewById(R.id.responsechat);
            sentchat = itemView.findViewById(R.id.sentchat);
            responsechat_view = itemView.findViewById(R.id.responsechat_view);
            sentchat_view = itemView.findViewById(R.id.sentchat_view);
        }
    }
}

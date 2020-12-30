package com.example.resultmanagement;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public UserAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ListItem listItem=listItems.get(position);
        holder.email.setText(listItem.getUsername());
        holder.username.setText(listItem.getSeatno());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You Clicked :"+listItem.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,View_Result_Details.class);
                intent.putExtra("username", ""+listItem.getUsername());
                intent.putExtra("email",""+listItem.getEmail());
                intent.putExtra("post_id",""+listItem.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
       public TextView username,email;
       public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.text_view_title);
            email=itemView.findViewById(R.id.text_view_description);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }

}

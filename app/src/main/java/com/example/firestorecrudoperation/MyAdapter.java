package com.example.firestorecrudoperation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestorecrudoperation.databinding.ItemviewBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<UserModel>allDataList;
    FirebaseFirestore db;
    public MyAdapter(Context context, ArrayList<UserModel> allDataList) {
        this.context = context;
        this.allDataList = allDataList;
        db = FirebaseFirestore.getInstance();
 }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new ViewHolder(view);}
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvName.setText(allDataList.get(position).getName());
        holder.binding.tvEmail.setText(allDataList.get(position).getEmail());
        holder.binding.tvPass.setText(allDataList.get(position).getPassword());
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("user").document(allDataList.get(position).getUserId()).delete();
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateData_Activity.class);
                intent.putExtra("key",allDataList.get(position).getUserId());
                context.startActivity(intent);
            }
        });
    }
    public int getItemCount() {
        return allDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemviewBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           binding=ItemviewBinding.bind(itemView);
        }
    }
}

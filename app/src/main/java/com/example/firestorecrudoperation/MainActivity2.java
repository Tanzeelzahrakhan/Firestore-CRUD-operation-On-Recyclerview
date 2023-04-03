package com.example.firestorecrudoperation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.firestorecrudoperation.databinding.ActivityMain2Binding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
ActivityMain2Binding binding;
FirebaseFirestore db;
MyAdapter adapter;
ArrayList<UserModel>allDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=FirebaseFirestore.getInstance();
        getAllData();}
    public void getAllData(){
        allDataList=new ArrayList<>();
        db.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error==null){
                    allDataList.clear();
                    List<UserModel>data=value.toObjects(UserModel.class);
                    allDataList.addAll(data);
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    adapter=new MyAdapter(MainActivity2.this,allDataList);
                    binding.recyclerview.setAdapter(adapter);
                }
            }
        });

    }
}
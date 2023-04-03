package com.example.firestorecrudoperation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firestorecrudoperation.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=FirebaseFirestore.getInstance();
        binding.btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.ediName.getText().toString();
                String email=binding.ediEmail.getText().toString();
                String password=binding.ediPass.getText().toString();
                String id=db.collection("user").document().getId();
                UserModel data=new UserModel(name,email,password,id);
                db.collection("user").document(id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if (task.isComplete()){
                          Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                      }
                      else {
                          Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                      }
                    }
                });
            }
        });
        binding.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
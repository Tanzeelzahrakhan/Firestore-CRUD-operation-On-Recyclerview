package com.example.firestorecrudoperation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firestorecrudoperation.databinding.ActivityUpdateDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class UpdateData_Activity extends AppCompatActivity {
ActivityUpdateDataBinding binding;
    FirebaseFirestore db;
    String key;

    ArrayList<UserModel> allDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        binding=ActivityUpdateDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        key = getIntent().getStringExtra("key");
        db.collection("user").document(key).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                UserModel model=value.toObject(UserModel.class);
                binding.ediName.setText(model.getName());
                binding.ediEmail.setText(model.getEmail());
                binding.ediPass.setText(model.getPassword());
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.ediName.getText().toString();
                String email = binding.ediEmail.getText().toString();
                String pass = binding.ediPass.getText().toString();
                db.collection("user").document(key).update("name",name,"email",email,"password",pass)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isComplete()){
                                    Toast.makeText(UpdateData_Activity.this, "Update", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(UpdateData_Activity.this, "error", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                            }
                        });
            }
        });
    }
}
package com.ziad.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register_activity extends AppCompatActivity {
    EditText Name;
    EditText Email;
    EditText Phone;
    EditText Password;
    Button button;
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name =findViewById(R.id.Name);
        Email = findViewById(R.id.Email1);
        Phone = findViewById(R.id.Phone);
        Password = findViewById(R.id.Password1);
        button = findViewById(R.id.Reg_btn);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String phone = Phone.getText().toString();
                String password = Password.getText().toString();
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            firebaseUser= task.getResult().getUser();
                            DatabaseReference newUser = reference.child(firebaseUser.getUid());
                            newUser.child("Full Name").setValue(name);
                            newUser.child("Email").setValue(email);
                            newUser.child("Phone").setValue(phone);
                            newUser.child("Password").setValue(password);
                            Intent intent = new Intent(Register_activity.this,Home_activity.class);
                            Toast.makeText(Register_activity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(Register_activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
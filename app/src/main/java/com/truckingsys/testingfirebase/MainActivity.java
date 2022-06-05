package com.truckingsys.testingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String userName, userPassword, userType;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edit_fname = findViewById(R.id.editTextTextPersonName);
        final EditText edit_lname = findViewById(R.id.editTextTextPersonName2);
        Button btn = findViewById(R.id.submit_btn);
        Button testBTN = findViewById(R.id.testingbtn);
        Button fbStore = findViewById(R.id.fbBtn);
        DAOemployee dao = new DAOemployee();
        db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(v->
        {
            employee emp = new employee(edit_fname.getText().toString(), edit_lname.getText().toString());
            dao.add(emp).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record Is Inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        testBTN.setOnClickListener(v->{
            userName = "root";
            userPassword = "000000";
            userType = "Employer";

            fAuth = FirebaseAuth.getInstance();

            fAuth.createUserWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        fbStore.setOnClickListener(v -> {
            String userName = "root";
            String userPassword = "admin";
            Map<String, Object> users = new HashMap<>();
            users.put("username",userName);
            users.put("password", userPassword);

            db.collection("Users")
                    .add(users)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}
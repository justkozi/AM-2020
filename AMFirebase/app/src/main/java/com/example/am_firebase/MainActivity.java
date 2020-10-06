package com.example.am_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AuthUI.IdpConfig> providers = Collections.singletonList(
            new AuthUI.IdpConfig.EmailBuilder().build()
    );
    final int RC_SIGN_IN = 69;
    FirebaseDatabase databaseRef;
    DatabaseReference AlaRef;
    DatabaseReference JadziaRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

        databaseRef = FirebaseDatabase.getInstance();
        AlaRef = databaseRef.getReference("Ala");
        JadziaRef = databaseRef.getReference("Jadzia");

        findViewById(R.id.JadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText)
                        findViewById(R.id.editText_J)).getText().toString();
                JadziaRef.setValue(text);
            }
        });
        findViewById(R.id.AlaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText)
                        findViewById(R.id.editText_A)).getText().toString();
                AlaRef.setValue(text);
            }
        });

        AlaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((EditText)findViewById(R.id.editText_A)).setText(
                        dataSnapshot.getValue().toString());
                //alaTv.setText("Ala zmienila sie na " +
                //        dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        JadziaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ((EditText)findViewById(R.id.editText_J)).setText(
                        dataSnapshot.getValue().toString());
                //jadTv.setText("Jadzia zmienila sie na " +
                //        dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "ZALOGOWANO", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "NIEZALOGOWANO", Toast.LENGTH_LONG).show();
            }
        }
    }
}
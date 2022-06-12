package com.iubh.boardgamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.iubh.boardgamer.Auth.ProfileActivity;
import com.iubh.boardgamer.GroupChat.GroupChatActivity;

public class MainSiteActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button toGroupChat;
    private Button toProfil;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_site);

        toGroupChat = (Button) findViewById(R.id.toGroupChat);
        toProfil = (Button) findViewById(R.id.toProfil);
        logout = (Button) findViewById(R.id.signOut);


        toGroupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainSiteActivity.this, GroupChatActivity.class));
            }
        });

        toProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainSiteActivity.this, ProfileActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainSiteActivity.this, MainActivity.class));
            }
        });
    }
}
package com.iubh.boardgamer.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iubh.boardgamer.MainActivity;
import com.iubh.boardgamer.R;
import com.iubh.boardgamer.Auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private EditText editTextnewGroupCode;

    private Button logout;
    private Button updategroup;

    private TextView fullNameTextView, emailTextView, groupTextview;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.signOut);
        updategroup = (Button) findViewById(R.id.updateGroup);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        userID = user.getUid();

        fullNameTextView = findViewById(R.id.fullNameTitle);
        emailTextView = findViewById(R.id.emailAddressTitle);
        groupTextview = findViewById(R.id.groupTitle);

        updategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextnewGroupCode = (EditText) findViewById(R.id.newGroupCode);
                String newGroupCode = editTextnewGroupCode.getText().toString().trim();

                Map map = new HashMap();
                map.put("group",newGroupCode);
                reference.child(userID).updateChildren(map);

            }
        });

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.getFullName();
                    String email = userProfile.getEmail();
                    String group = userProfile.getGroup();

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    groupTextview.setText(group);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }
}

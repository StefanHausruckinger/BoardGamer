package com.iubh.boardgamer.GroupChat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iubh.boardgamer.Adapters.MessageAdapter;
import com.iubh.boardgamer.Auth.User;
import com.iubh.boardgamer.R;

import java.util.ArrayList;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase reference;
    private String userID;
    private DatabaseReference messagedb;
    private MessageAdapter messageAdapter;
    List<Message> messages;
    User u;

    private ImageButton send_txt;
    private EditText editTextInsertText;
    private RecyclerView rvMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        init();


    }

    private void init()
    {
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance();
        u = new User();

        rvMessage = (RecyclerView) findViewById(R.id.chat);
        editTextInsertText = (EditText) findViewById(R.id.insertText);
        send_txt = (ImageButton) findViewById(R.id.send_txt);
        send_txt.setOnClickListener(this);
        messages = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {

        if(!TextUtils.isEmpty(editTextInsertText.getText().toString()))
        {
            Message message = new Message(editTextInsertText.getText().toString(),u.getFullName());
            editTextInsertText.setText("");
            messagedb.push().setValue(message);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "You cannot send blank message!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();

        u.setUid(currentUser.getUid());
        u.setEmail(currentUser.getEmail());



        reference.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u = snapshot.getValue(User.class);
                u.setUid(currentUser.getUid());
                Method.name = u.getFullName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        messagedb = reference.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());
                messages.add(message);
                displayMessages(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for(Message m: messages)
                {
                    if(m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(message);
                    }
                    else
                    {
                        newMessages.add(m);
                    }
                }

                messages = newMessages;

                displayMessages(messages);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                Message message = snapshot.getValue(Message.class);

                message.setKey(snapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for (Message m:messages)
                {
                    if(!m.getKey().equals(message.getKey()))
                    {
                        newMessages.add(m);
                    }
                }

                messages = newMessages;
                displayMessages(messages);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        messages = new ArrayList<>();
    }

    private void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(GroupChatActivity.this));
        //rvMessage.setHasFixedSize(true);
        messageAdapter = new MessageAdapter(GroupChatActivity.this.messages,messagedb);
        rvMessage.setAdapter(messageAdapter);

    }
}
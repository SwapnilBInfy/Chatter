package com.example.swapnilbasu.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.swapnilbasu.chatter.NewActivity;
import com.example.swapnilbasu.chatter.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private Button mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private com.example.swapnilbasu.chatter.ChatMessageAdapter mAdapter;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mAdapter = new com.example.swapnilbasu.chatter.ChatMessageAdapter(this, new ArrayList<com.example.swapnilbasu.chatter.ChatMessage>());
        mListView.setAdapter(mAdapter);

        button = (Button) findViewById(R.id.mainBtn);
        button.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NewActivity.class);
                startActivity(i);
            }
        });


//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                sendMessage(message);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });
    }

    private void sendMessage(String message) {
        String[] split = message.split(" ");
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        //respond as Helloworld

        if(split[1].matches("graph")) {
            returnGraph(message);
        }

        else if(message.matches("Get Time")){
            sendAsyncTask(message);
        }

        else {
            mimicOtherMessage(message.toUpperCase());
        }



    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
    }

    private void returnGraph(String message) {
        String[] split = message.split(" ");
        String pairs = message.substring(message.indexOf('[')+1,message.indexOf(']'));
        ChatMessage chatMessage = new ChatMessage(message, true, true, split[0], pairs);
        mAdapter.add(chatMessage);
    }

    private void sendAsyncTask(String message){
        ChatMessage chatMessage = new ChatMessage(message, false, true);
        mAdapter.add(chatMessage);

    }


}

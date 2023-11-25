package com.example.voyagevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    RecyclerView rv;
    EditText prompt;
    ImageView sendchatbtn;
    ArrayList<Chat> chatList;
    ChatAdapter chatAdapter;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(60000, TimeUnit.MILLISECONDS).build();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setSelectedItemId(R.id.item_1);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_2) {
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        rv = findViewById(R.id.recyclerView);
        prompt = findViewById(R.id.chatmessage);
        sendchatbtn = findViewById(R.id.sendmessage);
        chatList = new ArrayList<Chat>();

        chatAdapter = new ChatAdapter(chatList);
        rv.setAdapter(chatAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rv.setLayoutManager(llm);
        addToChat("Welcome to VoyageVista's chatbot, powered by ChatGPT. Let me help you with your travels!", "bot");

        sendchatbtn.setOnClickListener((v) ->{
            String enteredpromt = prompt.getText().toString().trim();
            if (enteredpromt.length()>0){
                addToChat(enteredpromt, "user");
                callChatGPT(enteredpromt+" I'm in " + userinfo.usercityname + " keep it brief");
                prompt.setText("");
                addToChat("Loading...", "bot");
            }
        });
    }

    private void callChatGPT(String enteredpromt) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","gpt-3.5-turbo");
            JSONArray messages = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put("role", "user");
            obj.put("content", enteredpromt);
            messages.put(obj);
            jsonBody.put("messages", messages);
            jsonBody.put("temperature",0.7);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        chatList.remove(chatList.size()-1);
                        addToChat(result.trim(), "bot");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    chatList.remove(chatList.size()-1);
                    addToChat("Failed to load response due to "+response.body().string(), "bot");
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                chatList.remove(chatList.size()-1);
                addToChat("Failed to load response due to "+e.getMessage(), "bot");
            }

        });
    }

    void addToChat(String message, String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatList.add(new Chat(message, sentBy));
                chatAdapter.notifyDataSetChanged();
                rv.scrollToPosition(chatAdapter.getItemCount());
            }
        });
    }
}

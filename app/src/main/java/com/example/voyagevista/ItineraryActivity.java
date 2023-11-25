package com.example.voyagevista;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ItineraryActivity extends AppCompatActivity {

    WebView webView1;
    ImageView imageView4, imageView6;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(600000, TimeUnit.MILLISECONDS).build();
    RelativeLayout loadingPanel;
    String savedhtml;
    int isStarred;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itinerary);
        imageView4 = findViewById(R.id.imageView4);
        imageView6 = findViewById(R.id.imageView6);
        savedhtml = "<p>No itinerary saved</p>";
        isStarred = 0;
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarred == 0){
                    saveItinerary(savedhtml);
                    imageView6.setImageResource(R.drawable.star2);
                    isStarred = 1;
                } else{
                    isStarred = 0;
                    saveItinerary("<p>No itinerary saved</p>");
                    imageView6.setImageResource(R.drawable.star1);
                }
            }
        });
        loadingPanel = findViewById(R.id.loadingPanel);
        webView1 = (WebView) findViewById(R.id.webview1);
        webView1.getSettings().setJavaScriptEnabled(true);
        callChatGPT("Give me a" + userinfo.desireddays + " day itinerary table for " + userinfo.usercityname + " in HTML format. Make sure it is coloured and presented well (minimalistic design, light green colours). Make sure there are a good number of activities as well. Include restaurant recommendations as well.\n");
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
                        String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content").trim();
                        result = result.substring(result.indexOf("<!DOCTYPE html>"));
                        result = result.substring(0, result.indexOf("</html>")+7);
                        changeUI(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{

                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

        });
    }

    void changeUI(String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingPanel.setVisibility(View.INVISIBLE);
                imageView6.setVisibility(View.VISIBLE);
                savedhtml = message;
                webView1.loadDataWithBaseURL(null,message, "text/html", "UTF-8",null);
            }
        });
    }

    void saveItinerary(String message){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "itinerary.txt"));
            writer.write(message.getBytes());
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.example.voyagevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SavedItineraryActivity extends AppCompatActivity {
    WebView webView2;
    LinearLayout linearLayout4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveditinerary);
        linearLayout4 = findViewById(R.id.linearLayout4);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        webView2 = (WebView) findViewById(R.id.webview2);
        webView2.getSettings().setJavaScriptEnabled(true);
        File path = getApplicationContext().getFilesDir();
        File readfrom = new File(path, "itinerary.txt");
        byte[] content = new byte[(int) readfrom.length()];
        try {
            FileInputStream stream = new FileInputStream(readfrom);
            stream.read(content);
            webView2.loadDataWithBaseURL(null, new String(content), "text/html", "UTF-8",null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

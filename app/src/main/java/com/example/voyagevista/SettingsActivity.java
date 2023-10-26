package com.example.voyagevista;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    Button btnsubmit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
        TextView editTextText3 = findViewById(R.id.editTextText3);
        editTextText3.setText(userinfo.usercityname);
        TextView editTextText4 = findViewById(R.id.editTextText4);
        editTextText4.setText(userinfo.Lat + ", " + userinfo.Long);
        EditText editTextText2 = findViewById(R.id.editTextText2);
        editTextText2.setText(userinfo.name);
        Button btnsubmit = findViewById(R.id.btnsubmit);
        editTextText2.addTextChangedListener(textWatcher);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userinfo.name = editTextText2.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Button btnsubmit = findViewById(R.id.btnsubmit);
                btnsubmit.setText("Submit");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
}

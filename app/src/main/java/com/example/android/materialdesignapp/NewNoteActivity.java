package com.example.android.materialdesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class NewNoteActivity extends AppCompatActivity {

    EditText title,desc;
    Toolbar toolbar;
    Intent intent;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        title=findViewById(R.id.editTitle);
        desc=findViewById(R.id.editDesc);
        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("  keep your notes  ");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_new_word,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
                 intent=new Intent();
                if (TextUtils.isEmpty(title.getText())) {
                    title.setError("this field can't be empty");
                } else if (TextUtils.isEmpty(desc.getText())) {
                    desc.setError("this field can't be empty");
                } else {
                    intent.putExtra("TITLE", title.getText().toString());
                    intent.putExtra("DESCRIPTION", desc.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            return true;
            }
        return super.onOptionsItemSelected(item);
    }
}

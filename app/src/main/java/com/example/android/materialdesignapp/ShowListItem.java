package com.example.android.materialdesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class ShowListItem extends AppCompatActivity {

    WordViewModel wordViewModel;
    LiveData<Word>word;
    TextView title,desc; int id;
    int modetext=1;
    EditText editTitle,editesc;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_item);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        editTitle=findViewById(R.id.editText);
        editesc=findViewById(R.id.editText2);
        editTitle.setEnabled(false);
        editesc.setEnabled(false);
        toolbar=findViewById(R.id.toolbar3);
        toolbar.setTitle("  keep your notes  ");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        wordViewModel= new ViewModelProvider(this).get(WordViewModel.class);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);
        word=wordViewModel.getNote(id);


        word.observe(this, new Observer<Word>() {
            @Override
            public void onChanged(Word word) {
                title.setText(word.getTitle());
                desc.setText(word.getDescription());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_list,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save1 && modetext==0) {
            if ( TextUtils.isEmpty(editTitle.getText()) ) {
               editTitle.setError("this field can't be empty");
            } else if (TextUtils.isEmpty(editesc.getText())) {
                editesc.setError("this field can't be empty");
            } else {
    wordViewModel.update(id,Objects.requireNonNull(editTitle.getText().toString().trim()), Objects.requireNonNull(editesc.getText().toString().trim()));
    modetext=1;
    title.setText(editTitle.getText().toString());  desc.setText(editesc.getText().toString());
    editTitle.setText(""); editesc.setText("");
    editTitle.setEnabled(false); editesc.setEnabled(false);
    title.setEnabled(true); desc.setEnabled(true);
            }
            return true;
        }
        else if(item.getItemId()==R.id.edit1 && modetext==1)
        {
            modetext=0;
            editTitle.setText(title.getText().toString()); editesc.setText(desc.getText().toString());
            title.setText(""); desc.setText("");
            title.setEnabled(false); desc.setEnabled(false);
            editTitle.setEnabled(true); editesc.setEnabled(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

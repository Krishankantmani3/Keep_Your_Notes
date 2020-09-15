package com.example.android.materialdesignapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements WordListAdapter.ClickEventInRecylerView{
   Toolbar toolbar;
   FloatingActionButton floatingActionButton;
    final static int NewWordActivity_Request_code=1;
    RecyclerView recyclerView;
    WordViewModel wordViewModel;
    WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("  keep your notes  ");
        recyclerView=findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        adapter = new WordListAdapter(this,this);
        DividerItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.recycler_divider)));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);


         wordViewModel=new ViewModelProvider(MainActivity.this).get(WordViewModel.class);


            wordViewModel.getAllwords().observe(this, new Observer<List<Word>>() {
                @Override
                public void onChanged(List<Word> words) {
                    adapter.setWord(words);
                }
            });

    }


    public  void addNewNote(View view)
    {
        Intent i=new Intent(this,NewNoteActivity.class);
        startActivityForResult(i,NewWordActivity_Request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NewWordActivity_Request_code && resultCode==RESULT_OK)
        {
            assert data != null;
            wordViewModel.insert(new Word(Objects.requireNonNull(data.getStringExtra("TITLE")), Objects.requireNonNull(data.getStringExtra("DESCRIPTION"))));
        }

    }


    @Override
    public void onWordClick(int position, int id) {
        Intent intent=new Intent(this,ShowListItem.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onWordLongClic(int position, final int id, final View view) {
         view.setBackgroundColor(Color.parseColor("#FF0000"));
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

        alertDialog2.setTitle("Confirm Delete...");
        alertDialog2.setMessage("Are you sure you want to delete this item?");
        alertDialog2.setCancelable(false);
        alertDialog2.setIcon(R.drawable.ic_delete_black_24dp);

// Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        view.setBackgroundColor(Color.parseColor("#F6EDD6"));
                       wordViewModel.delete(id);
                        Toast.makeText(getApplicationContext(),
                                "item deleted", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
// Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        view.setBackgroundColor(Color.parseColor("#F6EDD6"));
                        Toast.makeText(getApplicationContext(),
                                "cancel", Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });

        alertDialog2.show();
    }
}

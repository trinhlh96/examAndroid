package com.example.examandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private Button btnAdd;
    private Button btnView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        dbHelper = new DBHelper(this);
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            onAdd();
        }
        if (view.getId() == R.id.btnView) {
            onView();
        }
    }

    private void onView() {
        Intent intent = new Intent(this, ListProduct.class);
        startActivity(intent);
    }

    private void onAdd() {
        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_LONG).show();
            return;
        }
        String isAdd = dbHelper.addProduct(edName.getText().toString(),edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ListProduct.class);
        startActivity(intent);
    }
}
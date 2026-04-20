package com.example.pliki;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private String FILE_NAME = "mojplik.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnRead = findViewById(R.id.btnRead);
        Button btnDelete = findViewById(R.id.btnDelete);


        btnCreate.setOnClickListener(v -> createFile());
        btnRead.setOnClickListener(v -> readFile());
        btnDelete.setOnClickListener(v -> deleteFileAction());
    }


    private void createFile() {
        String text = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Plik utworzony", Toast.LENGTH_SHORT).show();
            editText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFile() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = fis.read()) != -1) {
                sb.append((char) i);
            }
            fis.close();
            textView.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            textView.setText(" plik nie istnieje");
        }
    }


    private void deleteFileAction() {
        boolean deleted = deleteFile(FILE_NAME);
        if (deleted) {
            Toast.makeText(this, "Plik usunięty", Toast.LENGTH_SHORT).show();
            textView.setText("Zawartość pliku:");
        } else {
            Toast.makeText(this, "Nie udało się usunąć pliku", Toast.LENGTH_SHORT).show();
        }
    }
}

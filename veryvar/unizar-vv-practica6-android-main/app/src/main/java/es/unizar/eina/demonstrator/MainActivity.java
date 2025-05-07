package es.unizar.eina.demonstrator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.changeText) {
            EditText editText = findViewById(R.id.inputField);
            editText.setText("Lalala");
        } else if (view.getId() == R.id.switchActivity) {
            Intent intent = new Intent(this, SecondActivity.class);
            EditText inputText = findViewById(R.id.inputField);
            intent.putExtra("input", inputText.getText().toString());
            startActivity(intent);
        } else if (view.getId() == R.id.sendEmail){
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Body Text");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
    }
}
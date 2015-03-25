package willsdev.will.trainerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by will on 3/19/15.
 */
public class CreateClientForm extends Activity {

    EditText nameFeild;
    EditText ageFeild;
    Button createCientButton;
    android.widget.ImageView imageView;
    String name; // These have to be class variables to avoid making them final, as much as I can tell it's because Java is weird?
    int age; // ""

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_form);
        createCientButton = (Button) findViewById(R.id.createButton);

        View.OnClickListener newClientListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nameFeild = (EditText) findViewById(R.id.nameFeild);
                    ageFeild = (EditText) findViewById(R.id.ageFeild);
                    name = nameFeild.getText().toString();
                    age = Integer.parseInt(ageFeild.getText().toString());

                } catch (Exception e) {
                    System.out.println("Input cleaning failure, but we didn't crash!");
                }

                Intent result = new Intent(getApplicationContext(), MainActivity.class); // new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
                result.putExtra("name", name);
                result.putExtra("age", age);


                setResult(Activity.RESULT_OK, result);
                finish();
            }
        };

        createCientButton.setOnClickListener(newClientListener);


    }
}


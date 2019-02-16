package com.name.filler.found;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static TextView displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create elements
        final Button displayButton = (Button)findViewById(R.id.displayButton);
        displayText = (TextView)findViewById(R.id.displayText);
        final SearchView searchBar = (SearchView)findViewById(R.id.searchBar);
        //Ensure open searchView
        searchBar.setIconifiedByDefault(false);
        //Initialize Click Listener
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = "https://found-server.herokuapp.com";
                    new RetrieveHTTPTask().execute(url);
                }catch(Exception e)
                {
                    System.out.print("aaaaaaaaaaaaaa" + e.toString());
                }
            }
        });

    }
    public static void setText(String text){
        displayText.setText(text);
    }
}

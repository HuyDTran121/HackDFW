package com.name.filler.found;

import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static TextView displayText;
    public static ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create elements
        final Button displayButton = (Button) findViewById(R.id.displayButton);
        displayText = (TextView) findViewById(R.id.displayText);
        final SearchView searchBar = (SearchView) findViewById(R.id.searchBar);
        //Ensure open searchView
        searchBar.setIconifiedByDefault(false);
        //Initialize items array
        //items = new ArrayList<>();
        //Make intent
        final Intent intent = new Intent(this, ItemViewActivity.class);
        final Intent intent2 = new Intent(this, Splash.class);
        //reading in from the file
        FileInputStream stream;
        ContextWrapper cw = new ContextWrapper(getContext());
        File data = new File(cw.getFilesDir()+"/data.dat");
        ObjectInputStream ostream;
        try {
            stream = new FileInputStream(data);
            ostream = new ObjectInputStream(stream);
            items = (ArrayList<Item>) ostream.readObject();


        } catch (Exception e) {
            e.printStackTrace();
        }


        //Initialize Click Listener
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = "https://found-server.herokuapp.com";
                    new RetrieveHTTPTask().execute(url);
                    //TESTING CODE
                    //TODO remove
                    //       items.add(new Item());
                    Item dummy = items.get(0);
                    ItemViewActivity.setItem(dummy);
                    startActivity(intent);


                } catch (Exception e) {
                    System.out.print("aaaaaaaaaaaaaa" + e.toString());
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public static void setText(String text) {
        displayText.setText(text);
    }

    public Context getContext(){
        return getBaseContext();
    }

    public static void addItem(Item item) {
        items.add(item);
    }

    public class RetrieveHTTPTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            try {
                return getHTML(params[0]);
            } catch (Exception e) {
                System.out.println(e);
            }
            return "No data found";
        }

        protected void onPostExecute(String result) {
            //if you had a ui element, you could display the title
            MainActivity.setText(result);
            //Add item
            //PREP CODE
            String name = "result";
            byte[] byteArray = {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0};    Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Point upperLeft = new Point(10, 10);
            Point lowerRight = new Point(20, 20);
            int id = 528709309;
            Item temp = new Item(name, image, upperLeft, lowerRight, id);
            System.out.println(temp + "LSDJEKRJGLIJFDLTESTETSETTSTETESTETETETSSETTESTETETETTETE");
            MainActivity.addItem(temp);
            FileOutputStream stream;
            File data;
            ObjectOutputStream ostream;
            try{
                ContextWrapper cw = new ContextWrapper(getContext());
                data = new File(cw.getFilesDir()+"/data.dat");
                stream = new FileOutputStream(data);
                if(!data.exists())
                    data.createNewFile();
                ostream = new ObjectOutputStream(stream);
                ostream.writeObject(MainActivity.items);
                ostream.close();

            }
            catch(Exception e){
                System.out.println(e + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                e.printStackTrace();
            }

        }

        public String getHTML(String urlToRead) throws Exception {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        }
    }

}

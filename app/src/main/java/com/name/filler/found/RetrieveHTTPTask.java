package com.name.filler.found;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            ContextWrapper cw = new ContextWrapper(get);
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

    public static String getHTML(String urlToRead) throws Exception {
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

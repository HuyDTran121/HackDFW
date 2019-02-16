package com.name.filler.found;

import android.os.AsyncTask;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveHTTPTask extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params){
        try{
            return getHTML(params[0]);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "No data found";
    }

    protected void onPostExecute(String result) {
        //if you had a ui element, you could display the title
        MainActivity.setText(result);
        System.out.println(result);
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

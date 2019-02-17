package com.name.filler.found;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static TextView displayText;
    private static ListView listView;
    public static ArrayList<Data> items = new ArrayList<>();
    public static String currentItem;
    public CustomListAdapter listAdapter;
    SearchView searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create elements
        final Button displayButton = (Button) findViewById(R.id.displayButton);
        displayText = (TextView) findViewById(R.id.displayText);
        searchBar = (SearchView) findViewById(R.id.searchBar);
        listAdapter = new CustomListAdapter(this, items);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);

        currentItem = "";
        //Ensure open searchView
        searchBar.setIconifiedByDefault(false);
        //Search View Listener
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                    String url = "https://found-server.herokuapp.com/search";
                    new RetrieveHTTPTask().execute(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        //Initialize Click Listener
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = "https://found-server.herokuapp.com/search";
                    new RetrieveHTTPTask().execute(url);


                } catch (Exception e) {
                    e.printStackTrace();
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

    public class RetrieveHTTPTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            try {
                int org_size_items=items.size();
                ArrayList<Data> result = getHTML(params[0]);
                items.addAll(result);
                //TODO
                return "Found " + items.size() + " item(s)";
            } catch (Exception e) {
                System.out.println(e);
            }
            return "No data found";
        }

        protected void onPostExecute(String result) {
            //if you had a ui element, you could display the title
            MainActivity.setText(result);
            listAdapter.notifyDataSetChanged();
        }


        public ArrayList<Data> getHTML(String urlToRead) throws Exception {
            items.clear();
            String item = searchBar.getQuery().toString();
            currentItem = item;
            JSONObject postSearchParam = new JSONObject();
            postSearchParam.put("term",item.substring(0,1).toUpperCase()+item.substring(1));
            String fullURL = urlToRead;
            StringBuilder result = new StringBuilder();
            URL url = new URL(fullURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            DataOutputStream printout = new DataOutputStream(conn.getOutputStream ());
            printout.writeBytes(postSearchParam.toString());
            printout.flush ();
            printout.close ();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            JSONObject getSearchParent = new JSONObject(result.toString());

            JSONArray getSearchData = getSearchParent.getJSONArray("data");
            ArrayList<Data> searchResults=new ArrayList<>(getSearchData.length());
            for(int i=0;i<getSearchData.length();i++){
                JSONObject getSearchChild=new JSONObject(getSearchData.get(i).toString());
                String searchRoom = getSearchChild.getString("room");
                JSONObject searchImage = getSearchChild.getJSONObject("image");
                JSONObject searchCoordinates = getSearchChild.getJSONObject("bounding");
                double x1 =searchCoordinates.getDouble("x1");
                double y2 =searchCoordinates.getDouble("y2");
                double x2 =searchCoordinates.getDouble("x2");
                double y1 =searchCoordinates.getDouble("y1");
                System.out.println("Z"+searchCoordinates);
                String searchImageBinary = searchImage.getString("$binary");
                byte[] decodedString = Base64.decode(searchImageBinary, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


                searchResults.add(new Data(searchRoom,decodedImage,x1,x2,y1,y2));
            }
            conn.disconnect();
            System.out.println("ZZZZZZZZZZZ"+searchResults);
            return searchResults;
        }
    }

}

package heh.be.delimmo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 5/19/17.
 */

public class MainActivity extends AppCompatActivity {

    private DownloadRealEstatesTask dret;

    private String result;

    public void showMap(View view) {
        dret = new DownloadRealEstatesTask();
        dret.execute();
    }

    public void displayMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    public class DownloadRealEstatesTask extends AsyncTask<String, Void, String> {

        @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://192.168.0.12/connect.php");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = URLEncoder.encode("root", "UTF-8") + "=" + URLEncoder.encode("root", "UTF-8") + "&"
                    + URLEncoder.encode("ihatemaria", "UTF-8") + "=" + URLEncoder.encode("ihatemaria", "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream is = urlConnection.getInputStream();
            result = readStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;
    }

    public String readStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            String line;
            br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        displayMap();
    }
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

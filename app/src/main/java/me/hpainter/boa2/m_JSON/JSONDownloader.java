package me.hpainter.boa2.m_JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class JSONDownloader extends AsyncTask<Void, Void, String> {

    Context c;
    String jsonURL;
    ListView lv;

    ProgressDialog pd;

    public JSONDownloader(Context c, String jsonURL, ListView lv) {
        this.c = c;
        this.jsonURL = jsonURL;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Downloading");
        pd.setMessage("Please Wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();

        if (jsonData.startsWith("Error")){
            String error = jsonData;
            Toast.makeText(c, error, Toast.LENGTH_SHORT).show();
        }else{
            //Parser
            new JSONParser(c, jsonData, lv).execute();
        }
    }

    private String download() {
        Object connection = Connector.connect(jsonURL);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            if(con.getResponseCode() == con.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "\n");
                }
                //Close Resources
                br.close();
                is.close();

                //Return JSON
                return jsonData.toString();
            }else{
                return "Error " + con.getResponseMessage();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }
}

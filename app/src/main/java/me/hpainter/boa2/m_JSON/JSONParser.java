package me.hpainter.boa2.m_JSON;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.hpainter.boa2.m_Model.User;
import me.hpainter.boa2.m_UI.CustomAdapter;

public class JSONParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<User> users = new ArrayList<>();

    public JSONParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please Wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if (isParsed) {
            //Bind
            lv.setAdapter(new CustomAdapter(c, users));
        }else{
            Toast.makeText(c, "Unable to parse, check your log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {
        try {
            JSONObject totalObject = new JSONObject(jsonData);
            JSONObject mainObject = totalObject.getJSONObject("mobileContentInitializeBootstrapResponse");
            JSONArray jsonArray = mainObject.getJSONArray("mobileContentVersion");

            users.clear();
            User user;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);

                String name = object.getString("name");
                String id = object.getString("id");
                String version = object.getString("version");
                String locale = object.getString("locale");

                user = new User();

                user.setName(name);
                user.setId(id);
                user.setVersion(version);
                user.setLocale(locale);

                users.add(user);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}

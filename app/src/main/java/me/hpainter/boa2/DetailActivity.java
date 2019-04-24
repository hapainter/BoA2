package me.hpainter.boa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView nameTxt, idTxt, versionTxt, localeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        idTxt = (TextView) findViewById(R.id.idDetailTxt);
        localeTxt = (TextView) findViewById(R.id.localeDetailTxt);
        versionTxt = (TextView) findViewById(R.id.versionDetailTxt);

        //Get Intent
        Intent i = this.getIntent();

        //Receive Data
        String name = i.getExtras().getString("NAME_KEY");
        String id = i.getExtras().getString("ID_KEY");
        String locale = i.getExtras().getString("LOCALE_KEY");
        String version = i.getExtras().getString("VERSION_KEY");

        //Bind Data
        nameTxt.setText(name);
        idTxt.setText(id);
        localeTxt.setText(locale);
        versionTxt.setText(version);


    }
}

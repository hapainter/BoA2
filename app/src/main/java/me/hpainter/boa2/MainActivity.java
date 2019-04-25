package me.hpainter.boa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import me.hpainter.boa2.m_JSON.JSONDownloader;

public class MainActivity extends AppCompatActivity {

    String jsonURL = "https://mservice.bankofamerica.com/mgateway/xservice/bootstrap/mobileContentInitializeBootstrap/v1/APPLE_HANDSET";
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        Button but = findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloader(MainActivity.this, jsonURL, lv).execute();
            }
        });
    }
}

package me.hpainter.boa2.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import me.hpainter.boa2.DetailActivity;
import me.hpainter.boa2.R;
import me.hpainter.boa2.m_Model.User;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<User> users;

    public CustomAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {

        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
        }

        TextView nameTxt = view.findViewById(R.id.nameTxt);
        TextView idTxt = view.findViewById(R.id.idTxt);
        TextView localeTxt = view.findViewById(R.id.localeTxt);
        TextView versionTxt = view.findViewById(R.id.versionTxt);

        User user = (User) this.getItem(i);

        final String name = user.getName();
        final String id = user.getId();
        final String locale = user.getLocale();
        final String version = user.getVersion();

        nameTxt.setText(name);
        idTxt.setText(id);
        localeTxt.setText(locale);
        versionTxt.setText(version);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openDetailActivity(name, id, locale, version);
            }
        });


        return view;
    }
    //Open the activity
    private void openDetailActivity(String...details) {
        Intent i = new Intent (c, DetailActivity.class);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("ID_KEY", details[1]);
        i.putExtra("LOCALE_KEY", details[2]);
        i.putExtra("VERSION_KEY", details[3]);

        c.startActivity(i);
    }
}

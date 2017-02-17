package hr.hrenic.reciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import hr.hrenic.model.Entry;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();

        TextView tv = (TextView) findViewById(R.id.text_view);

        String json;

        switch (action) {


            case Intent.ACTION_MAIN:
                Toast.makeText(
                        this,
                        "Used only when data is sent from another app",
                        Toast.LENGTH_LONG
                ).show();
                break;


            case Entry.ACTION_SEND:
                json = intent.getStringExtra(Entry.EXTRA);
                try {
                    json = new JSONArray(json).toString(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tv.setText(json);
                break;


            case Entry.ACTION_SEND_MULTI:

                ArrayList<Entry> entries = intent.getParcelableArrayListExtra(Entry.EXTRA);
                json = "error";
                try {
                    json = Entry.toJSON(entries).toString(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tv.setText(json);
                break;

        }
    }
}

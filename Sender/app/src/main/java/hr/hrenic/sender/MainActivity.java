package hr.hrenic.sender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        ArrayList<Entry> entries = Entry.getEntries();
        try {
            JSONArray array = Entry.toJSON(entries);
            ((TextView) findViewById(R.id.tv_show)).setText(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        findViewById(R.id.send_json).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.this.sendJSON(v);
                    }
                }
        );

        findViewById(R.id.send_parc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.sendParcelable(v);
            }
        });
    }

    /**
     * Send entries using JSON serialization
     *
     * @param view
     */
    public void sendJSON(View view) {
        ArrayList<Entry> entries = Entry.getEntries();
        String json = "error";
        try {
            json = Entry.toJSON(entries).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();
        intent.setAction(Entry.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Entry.EXTRA, json);

        startIntent(intent);
    }

    public void sendParcelable(View view) {
        ArrayList<Entry> entries = Entry.getEntries();

        Intent intent = new Intent();
        intent.setAction(Entry.ACTION_SEND_MULTI);
        intent.setType("text/plain");
        intent.putParcelableArrayListExtra(Entry.EXTRA, entries);

        startIntent(intent);
    }

    private void startIntent(Intent intent) {
        intent = Intent.createChooser(intent, "Choose");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Toast.makeText(this, "Intent sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No application can handle intent", Toast.LENGTH_LONG).show();
        }
    }
}

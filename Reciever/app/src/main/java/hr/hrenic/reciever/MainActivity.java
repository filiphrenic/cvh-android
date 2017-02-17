package hr.hrenic.reciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    static String ACTION_SEND = "hr.hrenic.action.SEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();

        TextView tv = (TextView) findViewById(R.id.text_view);

        if (Intent.ACTION_MAIN.equals(action)) {
            Toast.makeText(
                    this,
                    "Used only when data is sent from another app",
                    Toast.LENGTH_LONG
            ).show();
        } else if (ACTION_SEND.equals(action)) {

            String json = intent.getStringExtra(Intent.EXTRA_TEXT);
            try {
                JSONArray jsonArray = new JSONArray(json);
                json = jsonArray.toString(2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tv.setText(json);

        } else {
            Toast.makeText(this, action, Toast.LENGTH_LONG).show();
        }
    }
}

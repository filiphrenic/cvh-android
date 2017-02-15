package hr.hrenic.sender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import hr.hrenic.sender.model.Entry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.send_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.this.send(v);
                    }
                }
        );
    }


    public void send(View view) {
        ArrayList<Entry> entries = Entry.getEntries();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        Bundle b = new Bundle();
        b.putString("hrenic", "dijela bogati");
        intent.putExtras(b);

//        intent.putParcelableArrayListExtra("hrenic", entries);
//        usingParcelable(intent, entries);
//        usingJSON(intent, entries);
        startIntent(intent);
    }


    private void usingParcelable(Intent intent, ArrayList<Entry> entries) {
        intent.putParcelableArrayListExtra("hrenic", entries);
    }

    private void usingJSON(Intent intent, ArrayList<Entry> entries) {
        // TODO
    }

    private void startIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Toast.makeText(this, "Intent sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No application can handle intent", Toast.LENGTH_LONG).show();
        }
    }
}

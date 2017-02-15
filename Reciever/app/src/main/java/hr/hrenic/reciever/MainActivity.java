package hr.hrenic.reciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        } else if (Intent.ACTION_SEND.equals(action)) {
//            ArrayList<Parcelable> xx = intent.getParcelableArrayListExtra("hrenic");
//            for (Parcelable p : xx) {
//                tv.append(p.toString());
//            }


            Bundle b = intent.getExtras();
            tv.setText(b.getString("hrenic", "default"));
        }
    }
}

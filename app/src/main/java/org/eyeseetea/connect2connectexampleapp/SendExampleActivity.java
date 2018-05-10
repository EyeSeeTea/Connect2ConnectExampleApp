package org.eyeseetea.connect2connectexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eyeseetea.connect2connectexampleapp.model.ConnectVoucher;

import java.io.IOException;
import java.io.InputStream;

public class SendExampleActivity extends AppCompatActivity {
    private static final String CONNECT_VOUCHER = "ConnectVoucher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_example);
        initViews();
    }

    private void initViews() {
        final EditText jsonToSend = findViewById(R.id.json_to_send);
        final Button sendJson = findViewById(R.id.send_json);
        Button resetJson = findViewById(R.id.reset_json);
        jsonToSend.setText(loadJSONFromAsset());
        sendJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendJsonText(jsonToSend.getText().toString());
            }
        });
        resetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetText(jsonToSend);
            }
        });
    }

    private void resetText(EditText jsonToSend) {
        jsonToSend.setText(loadJSONFromAsset());
    }

    private void sendJsonText(String jsonToSend) {
        if (validateJson(jsonToSend)) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(
                    getString(R.string.connect_package));
            if (launchIntent != null) {
                Bundle mCredentialsBundle = new Bundle();
                mCredentialsBundle.putString(CONNECT_VOUCHER, jsonToSend);
                launchIntent.putExtras(mCredentialsBundle);
                startActivity(launchIntent);
            }
        }
    }

    private boolean validateJson(String jsonToSend) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ConnectVoucher connectVoucher = mapper.readValue(jsonToSend, ConnectVoucher.class);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.format_error, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open(getString(R.string.json_file_name));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

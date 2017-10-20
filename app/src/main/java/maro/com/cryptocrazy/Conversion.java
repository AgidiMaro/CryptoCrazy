package maro.com.cryptocrazy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static maro.com.cryptocrazy.R.id.query;

/**
 * Created by Emmanuel on 10/10/2017.
 */

public class Conversion extends AppCompatActivity {
    static String queryValue; //value entered
    static Double result; // result of conversion process
    EditText queryEditTextView;
    Button button;
    TextView resultTextView;
    String query_value;// value from mainActivity
    Intent in;
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        resultTextView = (TextView) findViewById(R.id.answer);
        queryEditTextView = (EditText) findViewById(query);
        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> spinnerArray = new ArrayList<String>();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        in = getIntent();

        query_value = in.getStringExtra("query_value");
        spinnerArray.add(MainActivity.getCryptoUnit());
        spinnerArray.add(in.getStringExtra("currencyType"));

        queryEditTextView.addTextChangedListener(cryptoToWorldWatcher);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    queryEditTextView.setHint("Amount in " + MainActivity.getCryptoUnit());
                    resultTextView.setText("Amount in " + in.getStringExtra("currencyType"));
                    queryEditTextView.addTextChangedListener(cryptoToWorldWatcher);

                }
                else if(position==1){
                        queryEditTextView.setHint("Amount in " + in.getStringExtra("currencyType"));
                        resultTextView.setText("Amount in " + MainActivity.getCryptoUnit());
                        queryEditTextView.addTextChangedListener(WorldToCryptoWatcher);

                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                queryEditTextView.addTextChangedListener(cryptoToWorldWatcher);

            }
        });

    }

    public static void convertCryptoToWorld(EditText query, String query_value, TextView answer) {
        queryValue = query.getText().toString();
        result = (Double.parseDouble(queryValue)) * (Double.parseDouble(query_value));
        answer.setText(String.format("%.2f", result));
    }

    private void convertWorldToCrypto(EditText query, String query_value, TextView answer) {
        queryValue = query.getText().toString();
        result = (Double.parseDouble(queryValue)) / (Double.parseDouble(query_value));
        answer.setText(String.format("%.2f", result));
    }

    private final TextWatcher cryptoToWorldWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0 || s.toString().equals("")) {
                queryEditTextView.setHint("Amount in " + MainActivity.getCryptoUnit());
                resultTextView.setText("Amount in " + in.getStringExtra("currencyType"));

            } else {
                convertCryptoToWorld(queryEditTextView, query_value, resultTextView);
            }
        }
    };
    private final TextWatcher WorldToCryptoWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length()==0||s.toString().equals("")) {
                resultTextView.setText("Amount in " + MainActivity.getCryptoUnit());
                queryEditTextView.setHint("Amount in " + in.getStringExtra("currencyType"));

                //resultTextView.setText("Amount in " + in.getStringExtra("currency"));
            } else {
                convertWorldToCrypto(queryEditTextView, query_value, resultTextView);
            }


        }
    };
}



package maro.com.cryptocrazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String cryptoUnit;
    private RecyclerView recyclerView;

    public static String getCryptoUnit() {
        return cryptoUnit;
    }

    public void setCryptoUnit(String cryptoUnit) {
        this.cryptoUnit = cryptoUnit;
    }

    //String cryptoUnit;
    private RecyclerView.Adapter adapter;
    private ArrayList<Currency> currencys;
    private static final String URL_DATA = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=INR,RUB,EGP,ZAR,GBP,CAD,CHF,CNY,JPY,USD,PKR,AED,GHS,NGN,SEK,MXN,KRW,EUR,MYR,AUD";
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        TabLayout.Tab firstTab = tabLayout.newTab();

        firstTab.setText("BTC");

        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("ETH"); // set the Text for the second Tab

        tabLayout.addTab(secondTab);

        loadRecyclerViewData("BTC");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        currencys.clear();
                        loadRecyclerViewData("BTC");

                        break;
                    case 1:
                        currencys.clear();
                        loadRecyclerViewData("ETH");

                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencys = new ArrayList<>();

    }

    private void loadRecyclerViewData(final String crypto) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject raw = new JSONObject(response);
                    JSONObject cryptoO = raw.getJSONObject(crypto);
                    setCryptoUnit(crypto);
                    //JSONObject resultTextView = raw.getJSONObject("BTC");

                    updateCurrencies(cryptoO);

                    adapter = new CurrencyAdapter(currencys, getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                startActivity(new Intent(getApplicationContext(), Error.class));
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void updateCurrencies(JSONObject o) throws JSONException {

        //
        Currency dollars = new Currency("Dollars", "USD", o.getString("USD"), R.mipmap.us);
        Currency krw = new Currency("SouthKorean Won", "KRW", o.getString("KRW"), R.drawable.krw);
        Currency aud = new Currency("Australian Dollar ", "AUD", o.getString("AUD"), R.drawable.aud);
        Currency aed = new Currency("UAE Dirham", "AED", o.getString("AED"), R.drawable.uae);
        Currency cad = new Currency("Canadian Dollar", "CAD", o.getString("CAD"), R.drawable.cad);
        Currency chf = new Currency("Swiss Franc", "CHF", o.getString("USD"), R.mipmap.chf);
        Currency cny = new Currency("Chinese Yen ", "CNY", o.getString("CNY"), R.drawable.cny);
        Currency egp = new Currency("Egyptian Pound", "EGP", o.getString("EGP"), R.drawable.egp);
        Currency pounds = new Currency("British Pound", "GBP", o.getString("GBP"), R.mipmap.uk);
        Currency eur = new Currency("Euro", "EUR", o.getString("EUR"), R.mipmap.eur);
        Currency ghs = new Currency("Ghana Cedi", "GHS", o.getString("GHS"), R.drawable.ghs);
        Currency inr = new Currency("Indian Rupee", "INR", o.getString("INR"), R.mipmap.inr);
        Currency jpy = new Currency("Japanese Yen", "JPY", o.getString("JPY"), R.mipmap.jpy);
        Currency myr = new Currency("Malaysian Ringgit", "MYR", o.getString("MYR"), R.drawable.malay);
        Currency mxn = new Currency("Mexican Peso", "MXN", o.getString("MXN"), R.drawable.mx);
        Currency ngn = new Currency("Naira", "NGN", o.getString("NGN"), R.drawable.ngn);
        Currency pkr = new Currency("Pakistani rupee", "PKR", o.getString("PKR"), R.drawable.pkt);
        Currency sek = new Currency("Swedish Krona", "SEK", o.getString("SEK"), R.drawable.swd);
        Currency rand = new Currency("South African Rand", "ZAR", o.getString("ZAR"), R.drawable.zar);
        Currency rub = new Currency("Russian Ruble", "RUB", o.getString("RUB"), R.drawable.rus);

        currencys.add(aud);
        currencys.add(aed);
        currencys.add(cad);
        currencys.add(chf);
        currencys.add(cny);
        currencys.add(egp);
        currencys.add(pounds);
        currencys.add(eur);
        currencys.add(ghs);
        currencys.add(inr);
        currencys.add(jpy);
        currencys.add(krw);
        currencys.add(myr);
        currencys.add(mxn);
        currencys.add(ngn);
        currencys.add(pkr);
        currencys.add(rub);
        currencys.add(sek);
        currencys.add(dollars);
        currencys.add(rand);

    }

}

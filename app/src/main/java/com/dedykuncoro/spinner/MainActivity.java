package com.dedykuncoro.spinner;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dedykuncoro.spinner.adapter.Adapter;
import com.dedykuncoro.spinner.app.AppController;
import com.dedykuncoro.spinner.data.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txt_hasil;
    Spinner spinner_pendidikan;
    ProgressDialog pDialog;
    Adapter adapter;
    List<Data> listPendidikan = new ArrayList<Data>();

    // sesuaikan dengan IP Address PC/laptop atau ip address emulator android 10.0.2.2
    public static final String url = "http://10.0.2.2/android/kuncoro_spinner/menu.php";

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String TAG_ID = "id";
    public static final String TAG_PENDIDIKAN = "pendidikan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_hasil = (TextView) findViewById(R.id.txt_hasil);
        spinner_pendidikan = (Spinner) findViewById(R.id.spinner_pendidikan);

        spinner_pendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                txt_hasil.setText("Pendidikan Terakhir : " + listPendidikan.get(position).getPendidikan());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        adapter = new Adapter(MainActivity.this, listPendidikan);
        spinner_pendidikan.setAdapter(adapter);

        callData();

    }

    private void callData() {
        listPendidikan.clear();

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        // Creating volley request obj
        JsonArrayRequest jArr = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                Data item = new Data();

                                item.setId(obj.getString(TAG_ID));
                                item.setPendidikan(obj.getString(TAG_PENDIDIKAN));

                                listPendidikan.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

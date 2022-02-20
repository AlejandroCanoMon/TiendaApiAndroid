package com.example.tiendaapi;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiendaapi.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private ArrayList<Category> category = new ArrayList<Category>();
    private JsonArrayRequest arrayRequest;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private CategoryAdapter categoryAdapter;

    private String urlGETAll = "http://localhost:3000/products/";
    private String urlPOST = "http://localhost:3000/products/create";
    private String urlPUT = "http://localhost:3000/products/update/";
    private String urlDelete = "http://localhost:3000/products/delete/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.category);

        dialog = new Dialog(this);
        getData();

    }

    private void getData() {
        arrayRequest = new JsonArrayRequest(urlGETAll, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        Category cat = new Category();
                        cat.setId(jsonObject.getInt("id"));
                        cat.setDescription(jsonObject.getString("description"));
                        cat.setPack(jsonObject.getBoolean("pack"));
                        cat.setName(jsonObject.getString("name"));
                        cat.setPrice(jsonObject.getInt("price"));


                        category.add(cat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(category);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);
    }


    private void adapterPush(ArrayList<Category> category) {
    categoryAdapter = new CategoryAdapter(this, category);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(categoryAdapter);
    }

    public void addCategory(View v){
        TextView close, judul;
        EditText cat;
        Button submit;

        dialog.setContentView(R.layout.activity_modcat);

        close = (TextView) dialog.findViewById(R.id.txtClose);
        judul = (TextView) dialog.findViewById(R.id.judul);

        judul.setText("Añadir");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        cat = (EditText) dialog.findViewById(R.id.cat);
        submit = (Button) dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = cat.getText().toString();
                Submit(data);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void Submit(String data) {
        StringRequest request = new StringRequest(Request.Method.POST, urlPOST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Funciona", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Fallo", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> params = new HashMap<>();
                params.put("cat", data);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }


}

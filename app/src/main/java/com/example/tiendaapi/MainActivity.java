package com.example.tiendaapi;

import android.app.Dialog;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tiendaapi.model.product;
import com.google.android.gms.analytics.ecommerce.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RequestQueue requestQueue;
    private SwipeRefreshLayout refresh;
    private ArrayList<Product> product = new ArrayList<>();
    private JsonArrayRequest arrayRequest;
    private RecyclerView recyclerView;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        recyclerView = (RecyclerView) findViewById(R.id.product);

        dialog = new Dialog(this);

        refresh.setOnRefreshListener(this);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                product.clear();
                getData();
            }
        })
    }

    private void getData() {
        arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i= 0 ; i<response.length() ; i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        Product cat = new Product();
                        cat.setId(JSONObject.getInt("id"));
                        cat.setCategory(jsonObject.getString("name"));
                        product.add(cat);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                adapterPush(product);
            }


        })
    }

    private void adapterPush(ArrayList<Product> product) {
    }

    @Override
    public void onRefresh() {

    }
}

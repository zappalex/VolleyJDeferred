package com.example.aashworth.volleyjdeferred;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.aashworth.volleyjdeferred.models.Blunt;
import com.example.aashworth.volleyjdeferred.models.Random;
import com.google.gson.Gson;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //private static final String URL = "http://cblunt.github.io/blog-android-volley/response.json";
    private static final String URL = "http://echo.jsontest.com/key/value/one/two";
    private static final String TAG = "MainActivity";

    @Bind(R.id.display_text)
    TextView mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void sendRequest(final Deferred deferred){

        JsonObjectRequest request = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        deferred.resolve(response);
                    }
                },

                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse( VolleyError error){
                        deferred.reject(error);
                    }
                }
        );

        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onFabClick(){

        Deferred deferred = new DeferredObject();
        Promise promise = deferred.promise();

        promise.done(new DoneCallback() {
            @Override
            public void onDone(Object result) {
                //WTF IS GOING ON HERE?

                Log.d(TAG, "result = " + result.toString());
                //Blunt blunt = new Gson().fromJson(result.toString(), Blunt.class );
                Random randomObj = new Gson().fromJson(result.toString(), Random.class);
                Log.d(TAG, "randomObj = " + randomObj.getKey());
                mDisplayText.setText(randomObj.getKey());

            }
        }).fail(new FailCallback() {
            @Override
            public void onFail(Object result) {
                mDisplayText.setText(result.toString());
            }
        });

        //pass to sendRequest
        sendRequest(deferred);

    }


}

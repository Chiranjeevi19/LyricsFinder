package chiranjeevi.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText edtArtistName,edtSongName;
    Button btnGetLyrics;
    TextView txtLyrics;
    //https://api.lyrics.ovh/v1/Rihanna/Diamonds#


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtArtistName=findViewById(R.id.edtArtistName);
        edtSongName=findViewById(R.id.edtSongName);
        btnGetLyrics=findViewById(R.id.btnGetLyrics);
        txtLyrics=findViewById(R.id.txtLyrics);

        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "This button is tapped", Toast.LENGTH_SHORT).show();
                String url="https://api.lyrics.ovh/v1/"+edtArtistName.getText().toString()+"/"+edtSongName.getText().toString();
                url.replace("","20%");
                RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            txtLyrics.setText(response.getString("lyrics"));

                        }catch(JSONException e){
                            e.printStackTrace();

                        }


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                requestQueue.add(jsonObjectRequest);


            }
        });

    }
}

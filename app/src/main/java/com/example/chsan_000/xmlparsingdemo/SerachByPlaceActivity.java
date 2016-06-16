package com.example.chsan_000.xmlparsingdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.chsan_000.pojo.MyResponse;
import com.example.chsan_000.pojo.Postalcodes;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chsan_000 on 6/8/2016.
 */
public class SerachByPlaceActivity extends AppCompatActivity {

    List<MyResponse> mList = new ArrayList<>();
    ListView listView;
    EditText editText;
    Button button;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_search);


        button = (Button) findViewById(R.id.my_search);
        editText = (EditText) findViewById(R.id.my_edit_text);
        listView = (ListView) findViewById(R.id.my_listView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide keyboard after clicking button
                v = getCurrentFocus();

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                String postCode = editText.getText().toString();
                if (postCode.isEmpty()) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SerachByPlaceActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.cloud);
                    alertDialogBuilder.setTitle("Zip Code Search");

                    alertDialogBuilder.setMessage("Please Enter valid Place Name!");


                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    editText.setText("");//free the edittext

                    //TODO:: database storage logic
                    //

                    //get data from server
                    loadJson(postCode);

                }
            }
        });
    }

    public void loadJson(String pinCode) {
        RestApi restApi = new RestApi();
        restApi.getService().getPostalCodeByPlace(pinCode, "deepak", new Callback<MyResponse>() {
            @Override
            public void success(final MyResponse myResponse, Response response) {

                List<Postalcodes> p = myResponse.getPostalcodes();
                if (p.isEmpty()) {
                    //Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SerachByPlaceActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.cloud);
                    alertDialogBuilder.setTitle("Zip Code Search");

                    alertDialogBuilder.setMessage("No Results Found!");

                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else {
                    PostAdapter adapt = new PostAdapter(getApplicationContext(), R.layout.tem_file, myResponse.getPostalcodes());
                    listView.setAdapter(adapt);
                }
            }

            @Override
            public void failure(RetrofitError error) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SerachByPlaceActivity.this);
                alertDialogBuilder.setMessage("Please check your internet connection");

                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }
}

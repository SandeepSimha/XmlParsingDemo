package com.company.sandeep.zipcodesearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.company.sandeep.database.DbOpenHelper;
import com.company.sandeep.database.MyShowHistory;
import com.company.sandeep.pojo.MyResponse;
import com.company.sandeep.pojo.Postalcodes;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    //http://api.geonames.org/postalCodeLookupJSON?postalcode=500082&username=demo

    //65939b5289079ee61ec8945e67833fd3

    List<MyResponse> mList = new ArrayList<>();
    ListView listView;
    EditText editText;
    Button button;
    ProgressBar progressBar;
    private TextInputLayout inputLayoutName;
    final String TAG = "MainActivity";

   // final CharSequence[] items = {"Copy", "Show on Maps", "Share", "Time Zone & Current Time"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.search);
        editText = (EditText) findViewById(R.id.edit_text);
        listView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);

        progressBar.setVisibility(View.INVISIBLE);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }

                //get text from edit text
                String postCode = editText.getText().toString();
                if (postCode.isEmpty()) {

                    inputLayoutName.setError(getString(R.string.err_msg_name));
                    // requestFocus(inputName);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                } else {
                    //TODO:: database storage logic
                    storeIntoDatabase(postCode);

                    //get data from server
                    progressBar.setVisibility(View.VISIBLE);
                    loadJson(postCode);
                }
            }
        });
    }

    public void loadJson(String pinCode) {
        RestApi restApi = new RestApi();
        //getting postal code or place name
        restApi.getService().getPostalCodeByPlace(pinCode, "deepak", new Callback<MyResponse>() {
            @Override
            public void success(final MyResponse myResponse, Response response) {

                List<Postalcodes> p = myResponse.getPostalcodes();

                if (p.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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
                    //setting to adapter
                    progressBar.setVisibility(View.VISIBLE);

                    PostAdapter adapt = new PostAdapter(getApplicationContext(), R.layout.tem_file, myResponse.getPostalcodes());
                    listView.setAdapter(adapt);

                    progressBar.setVisibility(View.GONE);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Snackbar snackbar = Snackbar.make(listView, "Click on Right side Buttons!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    });

//
//                    //TODO:// Onitem click
//                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                        @Override
//                        public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
//                            final Postalcodes p = (Postalcodes) parent.getAdapter().getItem(position);
//
//
//                            TextView pin = (TextView) view.findViewById(R.id.txt_pin);
//                            final String myPin = pin.getText().toString();
//
//                            TextView country = (TextView) view.findViewById(R.id.txt_country);
//                            final String myCountry = country.getText().toString();
//
//                            TextView state = (TextView) view.findViewById(R.id.txt_state);
//                            final String myState = state.getText().toString();
//
//                            TextView village = (TextView) view.findViewById(R.id.txt_village);
//                            final String myVillage = village.getText().toString();
//
//                            //I am etting all details here
//                            //parent.getItemAtPosition(position);
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                            builder.setTitle("Options");
//                            builder.setItems(items, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    //   Toast.makeText(getBaseContext(), "Clekd at"+ items[which],Toast.LENGTH_LONG).show();
//                                    //items[which].
//                                    if (items[which] == items[0]) {
//                                        //copy to cliptboard
//
//                                        ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                                        ClipData clipData;
//                                        clipData = ClipData.newPlainText("text", p.getPostalcode() + "\n" + p.getCountryCode() + "\n" + myState + "\n" + myVillage);
//                                        clipBoard.setPrimaryClip(clipData);
//
//                                        Toast.makeText(view.getContext(), "Text Copied to clipboard!", Toast.LENGTH_SHORT).show();
//
//                                    } else if (items[which] == items[1]) {
//                                        //start an intent
//                                        Intent intent = new Intent(MainActivity.this, Mylocation.class);
//                                        //intent.putExtra();
//                                        intent.putExtra("langitude", p.getLng());
//                                        intent.putExtra("lattitude", p.getLat());
//                                        intent.putExtra("placename", p.getPlaceName());
//
//                                        startActivity(intent);
//                                        Toast.makeText(view.getContext(), "Show on maps clicked!", Toast.LENGTH_SHORT).show();
//                                    } else if (items[which] == items[2]) {
//                                        //start an intent
//                                        Intent intent = new Intent(Intent.ACTION_SEND);
//                                        intent.putExtra(Intent.EXTRA_TEXT, "PinCode: " + myPin + "\nCountry: " + myCountry + "\nState: " + myState + "\nVillage: " + myVillage);
//                                        intent.setType("text/plain");
//                                        startActivity(intent);
//                                        Toast.makeText(view.getContext(), "Shared Successfully!", Toast.LENGTH_SHORT).show();
//                                    } else if (items[which] == items[3]) {
//                                        //start an intent
//                                        Intent intent = new Intent(MainActivity.this, TimeZoneActivity.class);
//
//                                        intent.putExtra("langitude", p.getLng());
//                                        intent.putExtra("lattitude", p.getLat());
//
//                                        startActivity(intent);
//                                        Toast.makeText(view.getContext(), "TimeZone Clicked on maps clicked!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                            //Toast.makeText(MainActivity.this, "Item clicked on: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                            return true;
//                        }
//                    });

                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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

    public void storeIntoDatabase(String postCode) {
        //TODO:: Write logic here
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        Long id = dbOpenHelper.insertZipCode(postCode);
        if (id < 0) {
            Log.e(TAG, "insertEmployee: employee data insertion failed !");
        } else {
            Log.i(TAG, "insertEmployee: employee data insertion Successfully done !");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //enable or disable menus
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,
                    "Hey There! Please download my at: https://vegetable-list.herokuapp.com/");
            intent.setType("text/plain");
            startActivity(intent);
        } else if (item.getItemId() == R.id.about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setIcon(R.drawable.about);

            builder.setMessage("Please share this app to your friends."+
                            "The data is not purely accursed");


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } else if (item.getItemId() == R.id.history) {
            //Toast.makeText(this, "History clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MyShowHistory.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
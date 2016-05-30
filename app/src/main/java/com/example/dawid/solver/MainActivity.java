package com.example.dawid.solver;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class MainActivity extends ActionBarActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private InputStream is;
    private InputStreamReader isr;
    private EditText editText;
    private TextView textView;
    private ArrayList< ArrayList<ArrayList<String>>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
         is = getResources().openRawResource(R.raw.dictionary);
        isr=new InputStreamReader(is);
         editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);



      list=new ArrayList<ArrayList<ArrayList<String>>>();
        for(int j=0;j<16;j++) {
            list.add(new ArrayList<ArrayList<String>>());
            for (int i = 0; i < 28; i++) {
                list.get(j).add(new ArrayList<String>());
            }
        }
        String line=new String ();
        try {
            //InputStream is = getResources().openRawResource();
            //FileReader fileReader=new FileReader("dictionary.txt");



            BufferedReader bufferedReader= new BufferedReader(isr);

            //Log.v("asdasd0","Zaczynam");
            while((line=bufferedReader.readLine())!=null){

                    //System.out.println(line);
                    list.get(line.length()).get(line.charAt(0)-'a').add(line);
                // Log.v("asd",line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }








    }

    public void accept(View view) {


        String txt = editText.getText().toString();


        int x = txt.charAt(0)-'0';











        ArrayList<ArrayList<String>> dictionary;
        ArrayList<String> words;
        ArrayList<String> propositions = new ArrayList<String>();
        Reader reader = new Reader();


        dictionary =list.get(x);
        System.out.println("znalazłem w słowniku " + dictionary.size() + " " + x + "-literowych wyrazow");
        char[] chars = new char[txt.length()-1 ];
        //System.out.println(args.length);
        for (int i = 1; i < txt.length(); i++) {
            chars[i - 1] = (char)txt.charAt(i);
        }
//        Log.v("tag",editText.getText().toString().charAt(0)+"" );
//        //System.out.println(chars);
//        Log.v("tag",new String(chars) );
//        Log.v("tag",x+"" );
       Permuter permuter = new Permuter(x, chars, dictionary);
        words = permuter.permute();
        System.out.println("znalazłem " + words.size() + " " + x + "-literowych permutacji");
        int j = 1;
        ArrayList<String> tmp = new ArrayList<String>();
        boolean ok;
        for (String word : words)
            for (String dict : dictionary.get(word.charAt(0) - 'a'))
                if (word.compareToIgnoreCase(dict) == 0) {
                    ok = true;
                    for (String s : propositions) {
                        if (s.compareToIgnoreCase(word) == 0) ok = false;
                    }
                    if (ok) {
                        propositions.add(word);
                        //  System.out.println("(" + (j++) + ") " + word);
                    }
                }

        Comparator<Object> comparator = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).compareToIgnoreCase((String)o2);
            }
        };

        //propositions.sort(comparator);
        Arrays.sort(propositions.toArray(), comparator);

        StringBuilder tmp2 = new StringBuilder();

        for (String s : propositions) {
            tmp2.append("(");
            tmp2.append(j++);
            tmp2.append(")");
            tmp2.append(s);
            tmp2.append("\n");

        }


      //  Log.v("asdasdasd",tmp2.toString());
    textView.setText(tmp2.toString());


    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.dawid.solver/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.dawid.solver/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


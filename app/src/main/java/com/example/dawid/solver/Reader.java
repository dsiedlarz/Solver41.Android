package com.example.dawid.solver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dawid on 14.04.16.
 */
public class Reader {



    public ArrayList<ArrayList<String>> read(int x){

        ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
        for(int i=0;i<28;i++){
            list.add(new ArrayList<String>());
        }
        String line=new String ();
        try {
            //InputStream is = getResources().openRawResource();
            FileReader fileReader=new FileReader("dictionary.txt");
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null){
                if(line.length()==x)
                    //System.out.println(line);
                list.get(line.charAt(0)-'a').add(line);
                Log.v("asd",line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }


}

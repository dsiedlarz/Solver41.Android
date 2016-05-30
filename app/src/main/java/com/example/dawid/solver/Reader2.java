package com.example.dawid.solver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dawid on 14.04.16.
 */
public class Reader2 {



    public ArrayList<String> read(){

       ArrayList<String> list=new ArrayList<String>();

        String line=new String ();
        try {
            FileReader fileReader=new FileReader("translator.txt");
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null){

                    list.add(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }


}

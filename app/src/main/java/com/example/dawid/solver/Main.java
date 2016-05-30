package com.example.dawid.solver;


import java.util.*;

/**
 * Created by dawid on 14.04.16.
 */
public class Main {

    public static void main(String[] args){
        ArrayList<ArrayList<String>> dictionary;
        ArrayList<String> words ;
        ArrayList<String> propositions = new ArrayList<String>();
        Reader reader= new Reader();

        int x=new Integer(args[0]);
        dictionary= reader.read(x);
        System.out.println("znalazłem w słowniku "+dictionary.size()+ " "+x+"-literowych wyrazow");
        char [] chars=new char[args.length-1];
        System.out.println(args.length);
        for (int i=1;i<args.length;i++) {
            chars[i-1]=args[i].charAt(0);
        }
        System.out.println(chars);
        Permuter permuter= new Permuter(x,chars,dictionary);
        words=permuter.permute();
        System.out.println("znalazłem "+words.size()+ " "+x+"-literowych permutacji");
        int j=1;
        ArrayList<String> tmp=new ArrayList<String>();
        boolean ok;
        for(String word:words)
            for(String dict:dictionary.get(word.charAt(0)-'a'))
                if(word.compareToIgnoreCase(dict)==0) {
                    ok=true;
                    for (String s:propositions){
                        if(s.compareToIgnoreCase(word)==0)ok=false;
                    }
                    if(ok) {
                        propositions.add(word);
                      //  System.out.println("(" + (j++) + ") " + word);
                    }
                }

        Comparator<String> comparator= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };

       // propositions.sort(comparator);

        for(String s:propositions){
            System.out.println("(" + (j++) + ") " + s);
        }


        HashSet<String> set = new HashSet(words);

        Reader2 r2=new Reader2();
        ArrayList<String> trans = r2.read();
int i=0;
        //System.out.println(trans);
        for(String word:set){
             //   System.out.println("("+(i++)+") "+word);


        }



    }


}

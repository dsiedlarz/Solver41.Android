package com.example.dawid.solver;

import java.util.ArrayList;

/**
 * Created by dawid on 14.04.16.
 */
public class Permuter {

    int x;
    char[] chars;
    int[] index;
    char[] tmp;
    ArrayList<ArrayList<String>> dictionary;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> tmp2;

    public Permuter(int x, char[] chars, ArrayList<ArrayList<String>> dictionary) {
        this.x = x;
        this.chars = chars;
        this.index=new int[x];
        this.tmp=new char[x];
        this.dictionary=dictionary;
    }

    public ArrayList<String> permute(){

check(x-1);

        return list;
    }

   public void check(int z){

       for(int i=0;i<chars.length;i++) {
          // System.out.println("char.length "+chars.length);
           //System.out.println("z: "+(x-z-1)+" i: "+i);
           if((x-z-1)==0)tmp2=dictionary.get(chars[i]-'a');
           index[x-z-1]=i;
           boolean ok=true;
           for(int j=0;j<x-z-1;j++){
               if(index[j]==i)ok=false;
           }
           if(ok) {
               char[] s=new char[x-z-1];
               for(int k=0;k<(x-z-1);k++){
                   s[k]=chars[index[k]];
               }
               String prefix=new String(s);
               boolean ok2=false;
               for(String word:tmp2){
                   if(word.startsWith(prefix)){
                       ok2=true;
                       break;
                   }
               }
                if(!ok2)continue;
               tmp[x - z-1] = chars[i];
               if (z == 0) {
                   list.add(new String(tmp));
                   //System.out.println(tmp);
               }
           if (z==0) continue;
           check(z - 1);
       }

       }
   }

}

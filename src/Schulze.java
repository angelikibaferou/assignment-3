import java.util.*;
import java.io.*;
import java.text.*;
import java.lang.*;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;


public class Schulze
{
   public static void main(String[] args){

   try{
    String arxeio = args[args.length-1];
    FileInputStream a = new FileInputStream(new File(arxeio));
    JSONTokener t = new JSONTokener(a);
    JSONObject ekloges = new JSONObject(t);

    JSONArray upopshfioi = ekloges.getJSONArray("candidates");
    JSONArray pshfodeltia = ekloges.getJSONArray("ballots");
    JSONArray pshfodeltio;


    int[][] pinakas = new int[upopshfioi.length()][upopshfioi.length()];

    for(int i=0;i < upopshfioi.length();i++){
        for(int j=0;j< upopshfioi.length();j++){
            pinakas[i][j] = 0;}}

    int f, g;
    for(int i=0; i < pshfodeltia.length(); i++){
        pshfodeltio = pshfodeltia.getJSONArray(i);
        for (int j=0; j<pshfodeltio.length(); j++){
            f = pshfodeltio.getInt(j);
            for(int k=j+1; k<pshfodeltio.length(); k++){
                g = pshfodeltio.getInt(k);
                pinakas[f][g] = pinakas[f][g] + 1; }}}


    /*dhmiourgia pinaka pred kai s*/
    int[][] s = new int[upopshfioi.length()][upopshfioi.length()];
    int[][] pred = new int[upopshfioi.length()][upopshfioi.length()];
    for(int i=0; i< upopshfioi.length();i++){
        for(int j=0; j< upopshfioi.length();j++){
            if(pinakas[i][j]>pinakas[j][i]){
                s[i][j] = pinakas[i][j] - pinakas[j][i];
                pred[i][j]= i;}
            else{
                s[i][j] =Integer.MIN_VALUE;
                pred[i][j]= -1;}
            }}

    for (int k=0; k<upopshfioi.length(); k++){
        for(int i=0; i<upopshfioi.length(); i++){
            if(i!=k){
                for(int j=0; j<upopshfioi.length(); j++){
                    if(j!=i){
                        if(s[i][j]<min(s[i][k],s[k][j])){
                            s[i][j] = min(s[i][k], s[k][j]);
                            pred[i][j] = pred[k][j];}}}}}}


    algorithmos(upopshfioi.length(), s, upopshfioi);
    }
        catch(IOException exc){
                           System.out.println("File Read Error");
         }
    }

    /*methodos eureshs elaxistou*/
     public static int min(int x, int y){
         int min = Integer.MIN_VALUE;
         if(x<y)
          min = x;
         else
          min = y;
         return min;
         }

    /*methodos gia ton trito algorithmo*/
    public static void algorithmos(int upopshfioi_length, int[][] s, JSONArray upopshfioi){
         ArrayList<String> lista;
          ArrayList<ArrayList<String>> nikhtes = new ArrayList<ArrayList<String>>();

          for(int i = 0; i<upopshfioi_length; i++) {
             lista = new ArrayList<String>();
             nikhtes.add(i, lista);

             for(int j = 0; j < upopshfioi_length; j++) {
                  if(i != j) {
                     if(s[i][j] > s[j][i])
                           lista.add(upopshfioi.getString(j));
                             }
                         }
                System.out.print(upopshfioi.getString(i)+ " = " + lista.size()+ " ");
               System.out.println(nikhtes.get(i));
            }

    }
}
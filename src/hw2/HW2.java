/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salim
 */
public class HW2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashSet<String> commons;
        HashMap<String, Integer> words;
        commons = commonWords();
          
        System.out.println("WORDS FOR dyssy10.txt");
        words = readBook("src\\doc\\dyssy10", commons);
        display200(words);
        words.clear();
        
        System.out.println("\nWORDS FOR tomsawyer.txt\n".toUpperCase());
        words = readBook("src\\doc\\tomsawyer",commons);
       display200(words);
        words.clear();
        
        System.out.println("\nWORDS FOR totc.txt\n".toUpperCase());
        words = readBook("src\\doc\\totc",commons);
        display200(words);
        words.clear();
        
        
    }
    
    
     public static HashSet<String> commonWords(){
        String word;
        HashSet<String> commons = new HashSet<String>();
        
        try {
            Scanner sc = new Scanner(new File("src\\doc\\commonwords.txt"));
            while(sc.hasNext()){
                word = sc.next().toLowerCase().toLowerCase().replaceAll("[^a-z]","");             
                commons.add(word);
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HW2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return commons;
    }
     
     public static HashMap<String, Integer> readBook(String adress, HashSet<String> commons){
         String word;
        HashMap<String,Integer> List = new HashMap<String, Integer>();
        
        try {
            Scanner sc = new Scanner(new File(adress + ".txt"));
            while(sc.hasNext()){
                String[] arr = sc.nextLine().toLowerCase().split("[^\\w']+");       //If there is 1000 word in text and there is 10 word per line, it does 1000+1000/10 operation.
                                                                                    //So n+n/10 ==> O(n)
                for (int i = 0; i < arr.length; i++) {
                    if(!commons.contains(arr[i])){
                        if(List.containsKey(arr[i]))
                            List.put(arr[i], List.get(arr[i])+1);
                        else
                            List.putIfAbsent(arr[i], 1);
                    
                        }
                }

            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HW2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
     }
     
     public static void display200(HashMap<String, Integer> words){
        Set list = words.entrySet();
        Object[] arr = list.toArray();
        for (int i = 1; i < arr.length; i++) {
            int num = Integer.parseInt(arr[i].toString().split("=")[1]);
            if(num > 200){
                String word = arr[i].toString().split("=")[0];
                System.out.println(word+" " + num);
            }
        }
     }
}



package com.ptasek.convertnumber;

import java.util.Random;

public class Number {
    Random rnd = new Random();

    // no need to comment, each method seems self-explanatory to me ^^
    public String dec2bin(){
        return Integer.toBinaryString(getRandomNumber());
    }


    public String bin2dec(String bin){
        return Integer.parseInt(bin, 2) + "";
    }

    public String hex2dec(String hex){
        return Integer.parseInt(hex, 16) + "";
    }

    public String dec2hex(){
        return Integer.toHexString(getRandomNumber());
    }


    public String dec(){
        return getRandomNumber() + "";
    }

    private String dec2bin(String dec){
        return Integer.toBinaryString(Integer.parseInt(dec));
    }

    private String dec2hex(String dec){
        return Integer.toHexString(Integer.parseInt(dec));
    }

    private String bin2hex(String bin){
        return Integer.toHexString(Integer.parseInt(bin, 2));
    }

    private String hex2bin(String hex){
        return Integer.toBinaryString(Integer.parseInt(hex,16));
    }


    private int getRandomNumber(){
        return rnd.nextInt(221) + 36;
    }

    // evaluates each input and return true if correct answer
    public boolean evaluate(String task, String result, String conversion){
        try {
            switch (conversion) {
                case "bin2hex":
                    return task.equals(bin2hex(result));
                case "bin2dec":
                    return task.equals(bin2dec(result));
                case "hex2dec":
                    return task.equals(hex2dec(result));
                case "hex2bin":
                    return task.equals(hex2bin(result));
                case "dec2hex":
                    return task.equals(dec2hex(result));
                case "dec2bin":
                    return task.equals(dec2bin(result));
            }
        }
        catch(Exception e) {
            return false;
        }
        return false;
    }
}

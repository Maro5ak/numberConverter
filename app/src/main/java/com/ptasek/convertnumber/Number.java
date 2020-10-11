package com.ptasek.convertnumber;

import java.util.Random;

public class Number {
    Random rnd = new Random();

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



    private int getRandomNumber(){
        return rnd.nextInt(221) + 36;
    }
}

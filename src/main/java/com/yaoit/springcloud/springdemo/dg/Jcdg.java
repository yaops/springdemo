package com.yaoit.springcloud.springdemo.dg;

public class Jcdg {

    public Integer dg(int n){

        if(n>=1){
            return  n*dg(n-1);

        }
        return 1;
    }

    public static void main(String[] args) {
        Jcdg jcdg = new Jcdg();
        Integer dg = jcdg.dg(5);
        System.out.println(dg);
    }
}

package com.example.veterineruygulamasi.Models;

public class KampanyaSilModel {
    private Boolean tf;
    private String text;

    public void setTf(Boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    @Override
     public String toString(){
        return 
            "KampanyaSilModel{" + 
            "tf = '" + tf + '\'' + 
            ",text = '" + text + '\'' + 
            "}";
        }
}

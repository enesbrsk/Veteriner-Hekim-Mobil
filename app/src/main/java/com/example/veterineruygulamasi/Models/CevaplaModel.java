package com.example.veterineruygulamasi.Models;

public class CevaplaModel {
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
            "CevaplaModel{" + 
            "tf = '" + tf + '\'' + 
            ",text = '" + text + '\'' + 
            "}";
        }
}

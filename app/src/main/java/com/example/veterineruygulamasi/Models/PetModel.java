package com.example.veterineruygulamasi.Models;

public class PetModel{
    private Boolean tf;
    private String petid;
    private String petresim;

    private String pettur;
    private Object petisim;
    private Object petcins;

    public void setTf(Boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setPetid(String petid){
        this.petid = petid;
    }

    public String getPetid(){
        return petid;
    }

    public void setPetresim(String petresim){
        this.petresim = petresim;
    }

    public String getPetresim(){
        return petresim;
    }


    public void setPettur(String pettur){
        this.pettur = pettur;
    }

    public String getPettur(){
        return pettur;
    }

    public void setPetisim(Object petisim){
        this.petisim = petisim;
    }

    public Object getPetisim(){
        return petisim;
    }

    public void setPetcins(Object petcins){
        this.petcins = petcins;
    }

    public Object getPetcins(){
        return petcins;
    }

    @Override
    public String toString(){
        return
                "PetModel{" +
                        "tf = '" + tf + '\'' +
                        ",petid = '" + petid + '\'' +
                        ",petresim = '" + petresim + '\'' +

                        ",pettur = '" + pettur + '\'' +
                        ",petisim = '" + petisim + '\'' +
                        ",petcins = '" + petcins + '\'' +
                        "}";
    }
}
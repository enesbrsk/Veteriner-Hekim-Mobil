package com.example.veterineruygulamasi.Models;

public class PetAdminModel{
    private Boolean tf;
    private Object petid;
    private Object petresim;
    private Object pettur;
    private Object petisim;
    private Object petcins;

    public void setTf(Boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setPetid(Object petid){
        this.petid = petid;
    }

    public Object getPetid(){
        return petid;
    }

    public void setPetresim(Object petresim){
        this.petresim = petresim;
    }

    public Object getPetresim(){
        return petresim;
    }

    public void setPettur(Object pettur){
        this.pettur = pettur;
    }

    public Object getPettur(){
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
            "PetAdminModel{" + 
            "tf = '" + tf + '\'' + 
            ",petid = '" + petid + '\'' + 
            ",petresim = '" + petresim + '\'' + 
            ",pettur = '" + pettur + '\'' + 
            ",petisim = '" + petisim + '\'' + 
            ",petcins = '" + petcins + '\'' + 
            "}";
        }
}

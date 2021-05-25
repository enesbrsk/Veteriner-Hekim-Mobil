package com.example.veterineruygulamasi.Models;

public class SoruModel {
    private String musid;
    private Boolean tf;
    private String soruid;
    private String telefon;
    private String kadi;
    private String soru;

    public void setMusid(String musid){
        this.musid = musid;
    }

    public String getMusid(){
        return musid;
    }

    public void setTf(Boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setSoruid(String soruid){
        this.soruid = soruid;
    }

    public String getSoruid(){
        return soruid;
    }

    public void setTelefon(String telefon){
        this.telefon = telefon;
    }

    public String getTelefon(){
        return telefon;
    }

    public void setKadi(String kadi){
        this.kadi = kadi;
    }

    public String getKadi(){
        return kadi;
    }

    public void setSoru(String soru){
        this.soru = soru;
    }

    public String getSoru(){
        return soru;
    }

    @Override
     public String toString(){
        return 
            "SoruModel{" + 
            "musid = '" + musid + '\'' + 
            ",tf = '" + tf + '\'' + 
            ",soruid = '" + soruid + '\'' + 
            ",telefon = '" + telefon + '\'' + 
            ",kadi = '" + kadi + '\'' + 
            ",soru = '" + soru + '\'' + 
            "}";
        }
}

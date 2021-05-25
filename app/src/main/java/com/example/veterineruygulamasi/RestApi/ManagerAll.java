package com.example.veterineruygulamasi.RestApi;

import com.example.veterineruygulamasi.Models.AdminKampanyaModel;
import com.example.veterineruygulamasi.Models.AnswersModel;
import com.example.veterineruygulamasi.Models.AsiEkle;
import com.example.veterineruygulamasi.Models.AsiModel;
import com.example.veterineruygulamasi.Models.AsiOnaylaModel;
import com.example.veterineruygulamasi.Models.AskQuestionModel;
import com.example.veterineruygulamasi.Models.CevaplaModel;
import com.example.veterineruygulamasi.Models.DeleteAnswerModel;
import com.example.veterineruygulamasi.Models.KampanyaEkleModel;
import com.example.veterineruygulamasi.Models.KampanyaModel;
import com.example.veterineruygulamasi.Models.KampanyaSilModel;
import com.example.veterineruygulamasi.Models.KullanicilerModel;
import com.example.veterineruygulamasi.Models.LoginModel;
import com.example.veterineruygulamasi.Models.PetAdminModel;
import com.example.veterineruygulamasi.Models.PetAsiTakipModel;
import com.example.veterineruygulamasi.Models.PetEkle;
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.Models.RegisterPojo;
import com.example.veterineruygulamasi.Models.SoruModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager{

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterPojo> kayitOl(String mail , String kadi, String parola)
    {
        Call<RegisterPojo> x = getRestApi().registerUser(mail,kadi,parola);
        return  x ;
    }

    public Call<LoginModel> girisYap(String mail , String parola)
    {
        Call<LoginModel> x = getRestApi().loginUser(mail,parola);
        return  x ;
    }

    public Call<List<PetModel>> getPets(String id)
    {
        Call<List<PetModel>> x = getRestApi().getPets(id);
        return  x ;
    }

    public Call<AskQuestionModel> sorSor(String id , String soru)
    {
        Call<AskQuestionModel> x = getRestApi().soruSor(id,soru);
        return  x ;
    }

    public Call<List<AnswersModel>> getAnswers(String id)
    {
        Call<List<AnswersModel>> x = getRestApi().getAnswers(id);
        return  x ;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String cevap , String soru)
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(cevap,soru);
        return  x ;
    }

    public Call<List<KampanyaModel>> getKampanya()
    {
        Call<List<KampanyaModel>> x = getRestApi().getKampanya();
        return  x ;
    }

    public Call<List<AsiModel>> getAsi(String id)
    {
        Call<List<AsiModel>> x = getRestApi().getAsi(id);
        return  x ;
    }

    public Call<List<AsiModel>> getGecmisAsi(String id,String pet_id)
    {
        Call<List<AsiModel>> x = getRestApi().getGecmisAsi(id,pet_id);
        return  x ;
    }


    public Call<List<SoruModel>> getSoru()
    {
        Call<List<SoruModel>> x = getRestApi().getSoru();
        return  x ;
    }

    public Call<CevaplaModel> cevapla(String musid, String soruid, String text)
    {
        Call<CevaplaModel> x = getRestApi().cevapla(musid,soruid,text);
        return  x ;
    }

    public Call<List<PetAsiTakipModel>> getAsiPet(String tarih)
    {
        Call<List<PetAsiTakipModel>> x = getRestApi().getPetAsiTakip(tarih);
        return  x ;
    }

    public Call<AsiOnaylaModel> asiOnayla(String id)
    {
        Call<AsiOnaylaModel> x = getRestApi().asiOnayla(id);
        return  x ;
    }

    public Call<AsiOnaylaModel> asiİptal(String id)
    {
        Call<AsiOnaylaModel> x = getRestApi().asiİptal(id);
        return  x ;
    }

    public Call<List<AdminKampanyaModel>> AdmingetKampanya()
    {
        Call<List<AdminKampanyaModel>> x = getRestApi().AdmingetKampanya();
        return  x ;
    }

    public Call<KampanyaEkleModel> addKampanya(String baslik,String icerik,String imageString)
    {
        Call<KampanyaEkleModel> x = getRestApi().addKampanya(baslik,icerik,imageString);
        return  x ;
    }

    public Call<KampanyaSilModel> kampanyaSil(String id)
    {
        Call<KampanyaSilModel> x = getRestApi().kampanyaSil(id);
        return  x ;
    }

    public Call<LoginModel> girisYapAdmin(String mail , String parola)
    {
        Call<LoginModel> x = getRestApi().loginAdmin(mail,parola);
        return  x ;
    }

    public Call<List<KullanicilerModel>> getKullaniciler()
    {
        Call<List<KullanicilerModel>> x = getRestApi().getKullaniciler();
        return  x ;
    }

    public Call<List<PetAdminModel>> getPetsAdmin(String id)
    {
        Call<List<PetAdminModel>> x = getRestApi().getPetsAdmin(id);
        return  x ;
    }

    public Call<PetEkle> petEkle(String musid,String isim, String tur, String cins,String resim)
    {
        Call<PetEkle> x = getRestApi().petEkle(musid,isim,tur,cins,resim);
        return  x ;
    }

    public Call<AsiEkle> asiEkle(String musid, String petid, String name,String tarih)
    {
        Call<AsiEkle> x = getRestApi().asiEkle(musid,petid,name,tarih);
        return  x ;
    }

}

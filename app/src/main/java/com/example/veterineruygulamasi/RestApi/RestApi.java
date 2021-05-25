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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/veterinerservis/kayitol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/veterinerservis/girisyap.php")
    Call<LoginModel> loginUser(@Field("mailadres") String mailadres, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetModel>> getPets(@Field("musid") String mus_id);

    @FormUrlEncoded
    @POST("/veterinerservis/sorusor.php")
    Call<AskQuestionModel> soruSor(@Field("id") String id, @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/veterinerservis/cevaplar.php")
    Call<List<AnswersModel>> getAnswers(@Field("mus_id") String mus_id);

    @FormUrlEncoded
    @POST("/veterinerservis/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("cevap") String cevapid, @Field("soru") String soruid);

    @GET("/veterinerservis/kampanya.php")
    Call<List<KampanyaModel>> getKampanya();

    @FormUrlEncoded
    @POST("/veterinerservis/asitakip.php")
    Call<List<AsiModel>> getAsi(@Field("id") String id);

    @FormUrlEncoded
    @POST("/veterinerservis/gecmisasi.php")
    Call<List<AsiModel>> getGecmisAsi(@Field("id") String id,@Field("petid") String petid);


    @GET("/veterinerservis/sorular.php")
    Call<List<SoruModel>> getSoru();

    @FormUrlEncoded
    @POST("/veterinerservis/cevapla.php")
    Call<CevaplaModel> cevapla(@Field("musid") String musid, @Field("soruid") String soruid, @Field("text") String text);

    @FormUrlEncoded
    @POST("/veterinerservis/veterinerasitakip.php")
    Call<List<PetAsiTakipModel>> getPetAsiTakip(@Field("tarih") String tarih);

    @FormUrlEncoded
    @POST("/veterinerservis/asionayla.php")
    Call<AsiOnaylaModel> asiOnayla(@Field("id") String petid);

    @FormUrlEncoded
    @POST("/veterinerservis/asiiptal.php")
    Call<AsiOnaylaModel> asiİptal(@Field("id") String petid);

    @GET("/veterinerservis/kampanyaidli.php")
    Call<List<AdminKampanyaModel>> AdmingetKampanya();

    @FormUrlEncoded
    @POST("/veterinerservis/kampanyaekle.php")
    Call<KampanyaEkleModel> addKampanya(@Field("baslik") String baslik, @Field("text") String text, @Field("resim") String resim);

    @FormUrlEncoded
    @POST("/veterinerservis/kampanyasil.php")
    Call<KampanyaSilModel> kampanyaSil(@Field("id") String kamid);

    @FormUrlEncoded
    @POST("/veterinerservis/girisyapAdmin.php")
    Call<LoginModel> loginAdmin(@Field("mailadres") String mailadres, @Field("sifre") String sifre);

    @GET("/veterinerservis/kullaniciler.php")
    Call<List<KullanicilerModel>> getKullaniciler();

    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetAdminModel>> getPetsAdmin(@Field("musid") String id);

    @FormUrlEncoded
    @POST("/veterinerservis/petekle.php")
    Call<PetEkle> petEkle(@Field("musid") String musid,@Field("isim") String isim, @Field("tur") String tur, @Field("cins") String cins
            , @Field("resim") String resim);

    @FormUrlEncoded
    @POST("/veterinerservis/asiekle.php")
    Call<AsiEkle> asiEkle(@Field("musid") String musid, @Field("petid") String petid, @Field("name") String name, @Field("tarih") String tarih
    );
}

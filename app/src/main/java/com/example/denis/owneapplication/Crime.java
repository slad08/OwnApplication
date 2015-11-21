package com.example.denis.owneapplication;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Denis on 22.10.2015.
 */
public class Crime {

    private UUID mId;
     private String mTitle_istochnik_dohoda;
    private String mTitle_summa_dohoda;
    private Date mDate;

    public Crime(){
        //Генерируем уникальный идентификатор
        mId=UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }


     public String toString(){
        return mTitle_istochnik_dohoda;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public String getTitle_istochnik_dohoda() {
        return mTitle_istochnik_dohoda;
    }

    public void setTitle_istochnik_dohoda(String Title_istochnik_dohoda) {
        this.mTitle_istochnik_dohoda = Title_istochnik_dohoda;
    }

    public String getTitle_summa_dohoda() {
        return mTitle_summa_dohoda;
    }

    public void setTitle_summa_dohoda(String Title_summa_dohoda) {
        this.mTitle_summa_dohoda = Title_summa_dohoda;
    }
}

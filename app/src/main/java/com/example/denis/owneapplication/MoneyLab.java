package com.example.denis.owneapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Denis on 29.10.2015.
 */
public class MoneyLab {
    private ArrayList<Crime> mCrimes;

    private static MoneyLab sMoneyLab;
    private Context mAppContext;


    private MoneyLab(Context AppContext){
        mAppContext=AppContext;
        mCrimes=new ArrayList<Crime>();


        for (int i=0; i<5; i++){
            Crime c=new Crime();
            c.setTitle_istochnik_dohoda("Источник дохода"+i);
            c.setTitle_summa_dohoda("Сумма дохода"+i);
            mCrimes.add(c);
        }
    }
    public static MoneyLab get(Context c){
        if (sMoneyLab==null){
            sMoneyLab=new MoneyLab(c.getApplicationContext());
        }
        return sMoneyLab;
    }
    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }
    public Crime getCrime(UUID id){
        for (Crime c:mCrimes){
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}

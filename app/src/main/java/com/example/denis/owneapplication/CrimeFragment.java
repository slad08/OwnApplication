package com.example.denis.owneapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;
import java.util.Date;
/**
 * Created by Denis on 22.10.2015.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID="owneapplication.crime_id";
    private static final String DIALOG_DATE = "date";

    private static final int REQUES_DATA=0;

    private Crime mCrime;
    private EditText mTitleIstDoh;
    private EditText mTitleSummaDoh;
    private Button mDateButton;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID crimeId=(UUID)getArguments().getSerializable(EXTRA_CRIME_ID);

        mCrime=MoneyLab.get(getActivity()).getCrime(crimeId);

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode==REQUES_DATA){
            Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);

            updateDate();
            }
    }

    public void updateDate(){
        mDateButton.setText(mCrime.getDate().toString());
    }
     @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,
                             Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragmen_crime,parent,false);

         mTitleIstDoh=(EditText)v.findViewById(R.id.title_istochnik_dohod);
         mTitleIstDoh.setText(mCrime.getTitle_istochnik_dohoda());
         mTitleIstDoh.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {


             }

             @Override
             public void onTextChanged(CharSequence c, int start, int before, int count) {
                 mCrime.setTitle_istochnik_dohoda(c.toString());
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });



         mDateButton=(Button)v.findViewById(R.id.button_time);
          updateDate();
         mDateButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 FragmentManager fm=getActivity()
                         .getSupportFragmentManager();
                 DatePickerFragment dialogg =DatePickerFragment
                         .newInstance(mCrime.getDate());
                 dialogg.setTargetFragment(CrimeFragment.this,REQUES_DATA);
                 dialogg.show(fm, DIALOG_DATE);

             }
         });



         mTitleSummaDoh=(EditText)v.findViewById(R.id.summa_dohoda);
         mTitleSummaDoh.setText(mCrime.getTitle_summa_dohoda());
         mTitleSummaDoh.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle_summa_dohoda(s.toString());
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
         return v;
    }
public static CrimeFragment newIntance(UUID crimeId){
    Bundle args=new Bundle();
    args.putSerializable(EXTRA_CRIME_ID,crimeId);
    CrimeFragment fragment=new CrimeFragment();
    fragment.setArguments(args);
    return fragment;
}


}

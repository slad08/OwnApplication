package com.example.denis.owneapplication;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Denis on 29.10.2015.
 */
public class MoneyListFragment extends ListFragment {

    private static final String TAG="MoneyListragment";

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = MoneyLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);


    }
    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
         Crime c=((CrimeAdapter)getListAdapter()).getItem(position);

      //запустим Crime Activity
        Intent i=new Intent(getActivity(),MoneyPagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(i);


     //   Log.d(TAG,c.getTitle_dohod()+"was clicked");
    }
    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Если мы не получили представление, заполняем его
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_money, null);
            }

            Crime c = getItem(position);

            TextView titleIstDohTextView =
                    (TextView) convertView.findViewById(R.id.ist_doh_list_item_titleTextView);

            titleIstDohTextView.setText(c.getTitle_istochnik_dohoda());



            TextView dateTextView = (TextView) convertView.findViewById(R.id.money_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            TextView summaTextView=(TextView)convertView.findViewById(R.id.summa_dohoda_ist_item_textView);

            summaTextView.setText(c.getTitle_summa_dohoda().toString());


             return convertView;

        }
    }
    @Override
    public void onResume(){
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

}

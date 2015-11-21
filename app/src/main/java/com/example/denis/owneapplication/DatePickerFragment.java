package com.example.denis.owneapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Denis on 09.11.2015.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE=
            "owneapplication.date";
    private Date mDate;

    public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
private void sendResult(int resultCode){
    if (getTargetFragment()==null)
        return;
    Intent i=new Intent();
    i.putExtra(EXTRA_DATE,mDate);

    getTargetFragment()
            .onActivityResult(getTargetRequestCode(),resultCode,i);
}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        mDate=(Date)getArguments().getSerializable(EXTRA_DATE);
        //Создание объект Calendar для получения года, месяца, дня
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        View v=getActivity().getLayoutInflater()
                    .inflate(R.layout.dialog_date, null);

        DatePicker datePicker=(DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                mDate=new GregorianCalendar(year,month,day).getTime();
                //обновление аргумента для сохранения выбрнного значения приповороте
                getArguments().putSerializable(EXTRA_DATE,mDate);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }


}

package com.example.denis.owneapplication;

import android.app.Fragment;

/**
 * Created by Denis on 29.10.2015.
 */
public class MoneyListActivity extends SingFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MoneyListFragment();
    }
}

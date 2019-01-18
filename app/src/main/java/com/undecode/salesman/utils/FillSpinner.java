package com.undecode.salesman.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.undecode.salesman.R;

import java.util.List;

/**
 * Created by mahmo on 4/17/2018.
 */

public class FillSpinner
{
    public void fillSpinner (Context context, List list, Spinner spinner)
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}

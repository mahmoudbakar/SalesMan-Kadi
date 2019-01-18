package com.undecode.salesman.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.undecode.salesman.R;


public class MyFragmentManager
{
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private int fragmentFrame;

    public MyFragmentManager(FragmentManager fragmentManager, int fragmentFrame)
    {
        this.fragmentManager = fragmentManager;
        this.fragmentFrame = fragmentFrame;
        this.fragment = fragmentManager.findFragmentById(fragmentFrame);
    }

    public void goToFragment(Fragment myFragment, String TAG)
    {
        fragment = fragmentManager.findFragmentById(fragmentFrame);
        if (fragment != null && fragment.isVisible())
        {
            //fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentManager.beginTransaction().replace(fragmentFrame, myFragment, TAG).commit();
        } else
        {
            fragmentManager.beginTransaction().add(fragmentFrame, myFragment, TAG).commit();
        }
        fragment = myFragment;
    }
}

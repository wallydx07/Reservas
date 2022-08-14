package com.example.reservas.view;
import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.reservas.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class nuevaReserva extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    TabItem tab1,tab2;
    PagerController pagercontroller;



@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.nuevareserva);
            tablayout=findViewById(R.id.tabnuevareserva);
            viewpager=findViewById(R.id.viewventa);
            tab1=findViewById(R.id.tabCLiente);
            tab2=findViewById(R.id.tabReserva);
            pagercontroller=new PagerController(getSupportFragmentManager(),tablayout.getTabCount());
            viewpager.setAdapter(pagercontroller);
            tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewpager.setCurrentItem(tab.getPosition());
                    if(tab.getPosition()==0){
                        pagercontroller.notifyDataSetChanged();
}
                    if(tab.getPosition()==1){
                        pagercontroller.notifyDataSetChanged();
}
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
}
}

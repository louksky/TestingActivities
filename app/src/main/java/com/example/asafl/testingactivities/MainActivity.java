package com.example.asafl.testingactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static ArrayList<ActivityElement> activityListcont = new ArrayList<>();
    static {

        activityListcont.add(new ActivityElement(Animtion.class,"Animation"));
        activityListcont.add(new ActivityElement(BirthDayChart.class,"BirthDay Chart"));
//        more homework..
    }

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    MyAdapter mAdapter;

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getBaseContext(), "Loading", Toast.LENGTH_LONG).show();
            ActivityElement clicked = (ActivityElement) view.getTag();
            if (clicked.getActivity() == null) {
                if (clicked.getName() != null) {
                    // we need to launch a different app here, if it's available.
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(clicked
                            .getName());
                    if (launchIntent == null) {
                        Toast.makeText(getBaseContext(), "Couldn't find activity for package:" +
                                clicked.getActivity(), Toast.LENGTH_LONG).show();

                        // example for removing item from the list
                        // ---------------------------------------
                        // Notice that we need to remove from the Model (the array)
                        // and not from the ListView (the View)!
                        // The view will be updated after the  notifyDataSetChanged();
                        activityListcont.remove(clicked);
                        mAdapter.notifyDataSetChanged();

                    } else {
                        startActivity(launchIntent);
                    }
                } else {
                    // nothing to do here
                    Toast.makeText(getBaseContext(), "Activity is not attached", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Intent intent = new Intent(getBaseContext(), clicked.getActivity());
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(activityListcont,mClickListener);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void onBdaysClick(View view) {
        startActivity(new Intent(this, Animtion.class));
    }


}

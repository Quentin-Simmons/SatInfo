package com.example.satinfo;

//public class OtherSatelliteAdapter {
//}

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OtherSatelliteAdapter extends RecyclerView.Adapter<OtherSatelliteAdapter.MyViewHolder> {
    private static final String TAG = "Satellite Adapter";

    //private String[] mSatellites;
    private ArrayList<Satellite> mSatellites;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.summary);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OtherSatelliteAdapter(ArrayList<Satellite> satellites) {
        mSatellites = satellites;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public OtherSatelliteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sat_summary, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String summary = String.valueOf( mSatellites.get(position).toString());
        holder.textView.setText(summary);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mSatellites.size();
    }
}
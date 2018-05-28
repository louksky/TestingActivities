package com.example.asafl.testingactivities;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private View.OnClickListener listener;
    private ArrayList<ActivityElement> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<ActivityElement> myDataset,
                     View.OnClickListener listener) {
        mDataset = myDataset;
        this.listener = listener;

        /**
         * Stable Id's are making change animation possible!
         * together with a unique getItemId() per row
         */
        setHasStableIds(true);
    }

    /**
     * Creates new views (invoked by the layout manager)
     * The parent will take care of recycling as needed according to the viewType, so no need to
     * worry about it.
     * The method will be called once per item on the screen, and not once per item in the list.
     * I.e. an item off the screen will automatically be recycled and used for one entering the
     * screen. Hence the name of the class (RecyclerList).
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // note: we can switch by the viewType, inflate or create a different View accordingly.

        // We create a new TextView for this simple example.
        // But it can be a ViewGroup with multiple Views instead.

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    /**
     * Replaces the contents of a view (invoked by the layout manager)
     * <p>
     * Here we are given the correct view for the position, inside the holder.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ActivityElement arrayItem = mDataset.get(position);
        holder.mTextView.setText("Open "+arrayItem.getName());
        holder.mTextView.setTag(arrayItem); // a handy way to pass any data to a view
        holder.mTextView.setOnClickListener(listener);

    }

    @Override
    public long getItemId(int position) {
        return mDataset.get(position).hashCode();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Provides a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder.
     *
     * In this example we supply only TextView
     * But we can hold any view type, or alternative view types if there are more than one row
     * type. (E.g. a title row and a data row)
     *
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        // will assign a click listener to it

        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}

package com.addit.drjainsoils.adapters.pairs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyRecyclerViewAdapter<T1 extends Pairs, T2> extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<T1> list;
    private Context context;

    // constructor

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    // getters

    public ArrayList<T1> getList() {
        return list;
    }

    public T1 getItem(int position) {
        return list.get(position);
    }

    // ArrayList methods

    public void addItem(T2 dataStorage) {
        T1 data = parse(dataStorage);
        if (data != null)
            list.add(data);
    }

    public void clearList() {
        list.clear();
    }

    // RecyclerView.Adapter methods

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // here according to our algorithm : viewType contains an integer value - which is = position of view in list
        // because the method "getItemViewType" returns position

        return RecyclerViewHolder.newInstance(parent, viewType, list.get(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        T1 dataStorage = list.get(position);
        try {
            dataStorage.bindView(context, position, dataStorage.getLayoutResID(), holder.itemView);
        } catch (Exception ex) {
            bindingErrorHandler(ex, dataStorage, dataStorage.getLayoutResID(), holder.itemView, position);
        }
    }

    public T1 parse(T2 data) {
        return (T1) data;
    }

    public View bindingErrorHandler(Exception ex, T1 data, @LayoutRes int viewType, @Nullable View view, int position) {
        return view;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        context = null;
        list.clear();
        list = null;
    }

    // Recycler ViewHolder

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ViewGroup parent;

        private RecyclerViewHolder(ViewGroup parent, View view) {
            super(view);
            this.parent = parent;
        }

    /*public static RecyclerViewHolder newInstance(ViewGroup parent, @LayoutRes int layoutRes) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return RecyclerViewHolder.newInstance(parent, view);
    }*/

        public static <T extends Pairs> RecyclerViewHolder newInstance(ViewGroup parent, int position, T dataStorage) {
            return RecyclerViewHolder.newInstance(parent, dataStorage.getView(parent.getContext(), position, null, parent));
        }

        public static RecyclerViewHolder newInstance(ViewGroup parent, View view) {
            return new RecyclerViewHolder(parent, view);
        }

        public ViewGroup getParent() {
            return parent;
        }
    }

    // OnItemClickListener abstract implementation

    public static abstract class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context) {
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mGestureDetector.onTouchEvent(e))
                this.onItemClick(childView, view.getChildAdapterPosition(childView));
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public abstract void onItemClick(View view, int position);
    }
}
package com.pkhope.diary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thinkpad on 2015/8/22.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  implements Filterable ,View.OnClickListener{
    private List<Diary> mItems;

    private  OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public RecyclerViewAdapter(List<Diary> list) {
        this.mItems = new ArrayList<Diary>(list);
    }

    public void setList(List<Diary> list){
        mItems.clear();
        mItems.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Diary item = mItems.get(position);
        if (item == null){
            return;
        }
        holder.mDate.setText(item.getDate());
        holder.mWeek.setText(item.getWeek());
        holder.mContent.setText(item.getContent());
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public Filter getFilter() {
        return new DiaryFilter();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener == null){
            return;
        }
        mOnItemClickListener.onItemClick(v,(Diary)v.getTag());
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mWeek;
        public TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mDate = (TextView) itemView.findViewById(R.id.tv_date);
            mWeek = (TextView) itemView.findViewById(R.id.tv_week);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public class DiaryFilter extends Filter {
        private List<Diary> mFilterItems;

        private DiaryFilter(){
            super();
            mFilterItems = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence){
            mFilterItems.clear();
            FilterResults filterResults = new FilterResults();
            if (charSequence.length() == 0){
                mFilterItems.addAll(mItems);
            }
            else {
                for (Iterator<Diary> iter = mItems.iterator(); iter.hasNext();){
                    Diary diary = iter.next();
                    if (diary.getContent().contains(charSequence)){
                        mFilterItems.add(diary);
                    }
                }
            }
            filterResults.values = mFilterItems;
            filterResults.count = mFilterItems.size();
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mItems.clear();
            mItems.addAll((ArrayList<Diary>) results.values);
            notifyDataSetChanged();
        }
    }
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,Diary diary);
    }
}

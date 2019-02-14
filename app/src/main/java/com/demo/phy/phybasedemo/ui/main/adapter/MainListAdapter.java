package com.demo.phy.phybasedemo.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;
import com.demo.phy.phybasedemo.data.bean.MainListBean;
import com.demo.phy.phybasedemo.widget.recycler.BaseLoadAdapter;

import java.util.ArrayList;

/**
 * Created by phy on 2019/1/15.
 */

public class MainListAdapter extends BaseLoadAdapter<MainListBean> {

    public interface LoadMoreListener {
        void loadMoreData();
    }

    public interface OnItemClickListener {
        void onItemBack(MainListBean item, int position);
    }

    private Context context;
    private int selectedPosition;
    private LoadMoreListener loadMoreListener;
    private OnItemClickListener mOnItemClickListener;

    public MainListAdapter(ArrayList<MainListBean> data) {
        this.list = data;
    }

    /**
     * 设置加载更多监听
     *
     * @param loadMoreListener
     * @param pageCount
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener, int pageCount) {
        this.loadMoreListener = loadMoreListener;
        setPageCount(pageCount);
    }

    /**
     * 设置itme点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 选中
     *
     * @param selectedPosition
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /**
     * 加载更多 - 回调
     */
    @Override
    protected void LoadingMore() {
        if (loadMoreListener == null) {
            return;
        }
        loadMoreListener.loadMoreData();
    }


    @Override
    protected RecyclerView.ViewHolder setItemViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
        ThisViewHolder holder = new ThisViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ThisViewHolder) holder).setData(list.get(position), position);
    }

    protected class ThisViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tv_name;
        private TextView tv_time;
        private ImageView img_icon;

        public ThisViewHolder(View view) {
            super(view);
            itemView = view;
            tv_name = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);
            img_icon = view.findViewById(R.id.img_icon);
        }


        /**
         * 数据绑定操作
         *
         * @param object
         * @param position
         */
        public void setData(final MainListBean object, final int position) {

            tv_name.setText(object.getName());
            tv_time.setText(object.getCreateTime());
//            img_icon.setVisibility(position == selectedPosition ? View.VISIBLE : View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.onItemBack(object, position);
                }
            });
        }
    }
}


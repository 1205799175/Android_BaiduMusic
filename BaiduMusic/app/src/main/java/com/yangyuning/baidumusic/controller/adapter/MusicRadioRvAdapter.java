package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRadioBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 乐库 电台 RecyclerView适配器
 */
public class MusicRadioRvAdapter extends RecyclerView.Adapter<MusicRadioRvAdapter.RadioViewHolder> {
    private Context context;
    private List<MusicRadioBean.ResultBean> datas;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 6;
    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 7;

    public MusicRadioRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRadioBean.ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_radio_rv, parent, false);
        RadioViewHolder radioViewHolder = new RadioViewHolder(view);
        return radioViewHolder;
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.imgId.setImageTintList(context.getResources().getColorStateList(R.color.colorMLRadioOne));
                break;
            case 1:
                holder.imgId.setImageTintList(context.getResources().getColorStateList(R.color.colorMLRadioTwo));
                break;
            case 2:
                holder.imgId.setImageTintList(context.getResources().getColorStateList(R.color.colorMLRadioThree));
                break;
            case 3:
                holder.imgId.setImageTintList(context.getResources().getColorStateList(R.color.colorMLRadioFour));
                break;
            default:
                holder.imgId.setImageTintList(context.getResources().getColorStateList(R.color.split_line_color));
        }
        holder.titleTv.setText(datas.get(position).getScene_name());
        Glide.with(context).load(datas.get(position).getIcon_android()).override(width, height).into(holder.imgId);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() >= 12 ? 12 : 0;
    }

    class RadioViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private ImageView imgId;

        public RadioViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_music_radio_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_music_radio_img);
        }
    }
}

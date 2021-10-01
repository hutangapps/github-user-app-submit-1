package com.gnm.gituser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterUser extends BaseAdapter {

    Context context;
    private ArrayList<ModelUser> heroes = new ArrayList<>();

    void setHeroes(ArrayList<ModelUser> heroes) {
        this.heroes = heroes;
    }

    AdapterUser(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int i) {
        return heroes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        ModelUser hero = (ModelUser) getItem(i);
        viewHolder.bind(hero);
        return itemView;
    }

    private static class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private CircleImageView imgPhoto;
        Context context;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_username);
            imgPhoto = view.findViewById(R.id.img_photo);
            context=view.getContext();
        }

        void bind(ModelUser hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getUsername());
            Glide.with(context)
                    .load(hero.getAvatar())
                    .into(imgPhoto);
        }
    }
}
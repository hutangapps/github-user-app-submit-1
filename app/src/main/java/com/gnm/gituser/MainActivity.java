package com.gnm.gituser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdapterUser adapter;
    private String[] dataName;
    private String[] dataUsername;
    private String[] dataLokasi;
    private String[] dataRepo;
    private String[] dataCompany;
    private String[] dataFollower;
    private String[] dataFollowing;
    private TypedArray dataPhoto;
    private ArrayList<ModelUser> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lv_list);
        adapter = new AdapterUser(this);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelUser user = new ModelUser();
                user.setName(userArrayList.get(i).getName());
                user.setUsername(userArrayList.get(i).getUsername());
                user.setFollowers(userArrayList.get(i).getFollowers());
                user.setFollowing(userArrayList.get(i).getFollowing());
                user.setCompany(userArrayList.get(i).getCompany());
                user.setLocation(userArrayList.get(i).getLocation());
                user.setRepository(userArrayList.get(i).getRepository());
                user.setAvatar(userArrayList.get(i).getAvatar());
                Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_PERSON, user);
                startActivity(moveWithObjectIntent);
            }
        });
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.name);
        dataUsername = getResources().getStringArray(R.array.username);
        dataPhoto = getResources().obtainTypedArray(R.array.avatar);
        dataLokasi = getResources().getStringArray(R.array.location);
        dataRepo = getResources().getStringArray(R.array.repository);
        dataCompany = getResources().getStringArray(R.array.company);
        dataFollower = getResources().getStringArray(R.array.followers);
        dataFollowing = getResources().getStringArray(R.array.following);
    }

    private void addItem() {
        userArrayList = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            ModelUser user = new ModelUser();
            user.setAvatar(dataPhoto.getResourceId(i, -1));
            user.setName(dataName[i]);
            user.setUsername(dataUsername[i]);
            user.setLocation(dataLokasi[i]);
            user.setRepository(dataRepo[i]);
            user.setCompany(dataCompany[i]);
            user.setFollowers(dataFollower[i]);
            user.setFollowing(dataFollowing[i]);
            userArrayList.add(user);
        }

        adapter.setHeroes(userArrayList);
    }
}
package com.gnm.gituser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_";
    TextView tvNama, tvUsername, tvLokasi, tvRepo, tvCompany, tvFollower, tvFollowing;
    CircleImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        tvNama = findViewById(R.id.txt_name);
        tvUsername = findViewById(R.id.txt_username);
        tvLokasi = findViewById(R.id.txt_lokasi);
        tvRepo = findViewById(R.id.txt_repo);
        tvCompany = findViewById(R.id.txt_company);
        tvFollower = findViewById(R.id.txt_follower);
        tvFollowing = findViewById(R.id.txt_following);
        imgPhoto = findViewById(R.id.img_photo);

        setData();
    }

    @SuppressLint("SetTextI18n")
    void setData(){
        ModelUser user = getIntent().getParcelableExtra(EXTRA_PERSON);

        assert user != null;
        tvNama.setText(user.getName());
        tvUsername .setText(user.getUsername());
        tvLokasi.setText("Lokasi: "+user.getLocation());
        tvRepo.setText("Repository: "+user.getRepository());
        tvCompany.setText("Company: "+user.getCompany());
        tvFollower .setText(user.getFollowers());
        tvFollowing .setText(user.getFollowing());
        Glide.with(this)
                .load(user.getAvatar())
                .into(imgPhoto);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
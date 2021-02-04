package com.craigke.letschat;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.craigke.letschat.adapter.ListImageAdapter;
import com.craigke.letschat.api.ApiClient;
import com.craigke.letschat.api.UnsplashInterface;
import com.craigke.letschat.models.Result;
import com.craigke.letschat.models.UnsplashAPIResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ListImageAdapter mAdapter;
    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        UnsplashInterface client= ApiClient.getClient().create(UnsplashInterface.class);
        Call<UnsplashAPIResponse> call = client.getSearchPhotos("Ideas", Constants.UNSPLASH_API);

        call.enqueue(new Callback<UnsplashAPIResponse>() {
            @Override
            public void onResponse(Call<UnsplashAPIResponse> call, Response<UnsplashAPIResponse> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    results = response.body().getResults();
                    mAdapter = new ListImageAdapter(SplashActivity.this, results);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(SplashActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showResults();

                }
            }

            @Override
            public void onFailure(Call<UnsplashAPIResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();

            }
            private void showResults() {
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showFailureMessage() {

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }

}


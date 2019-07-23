package app.simpleapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import app.simpleapp.Adapters.NoticeAdapter;
import app.simpleapp.Models.Notice;
import app.simpleapp.R;

public class NoticeListActivity extends AppCompatActivity implements NoticeListContract.View{

    private NoticeListPresenter fetchPresenter;
    private NoticeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Notice> mNotices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        fetchPresenter = new NoticeListPresenter(this);
       // fetchPresenter.fetchResultsUsingRxJava();
        fetchPresenter.fetchResults();
    }

    private void initializeViews() {
        mRecyclerView = findViewById(R.id.recycler_view_notice_list);
        mAdapter = new NoticeAdapter(mNotices);
        mLayoutManager = new LinearLayoutManager(NoticeListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() { }

    @Override
    public void hideProgress() { }

    @Override
    public void resultFail(Throwable t) {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void resultSuccess(ArrayList<Notice> noticeArrayList) {
        if (noticeArrayList != null && noticeArrayList.size() != 0) {
            mNotices.clear();
            mNotices.addAll(noticeArrayList);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(this, "RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }
}

package leevin.retrofitdemo.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import leevin.retrofitdemo.R;
import leevin.retrofitdemo.Utils.GlideRoundTransform;
import leevin.retrofitdemo.Utils.ToastUtil;
import leevin.retrofitdemo.exception.ApiErrorUtils;
import leevin.retrofitdemo.model.api.ApiGeneratorImpl;
import leevin.retrofitdemo.model.api.UserApi;
import leevin.retrofitdemo.model.entity.ApiError;
import leevin.retrofitdemo.model.entity.BaseResponse;
import leevin.retrofitdemo.model.entity.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static leevin.retrofitdemo.Utils.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TaskContract.View {

    private static final String TAG = "MainActivity";
    private ContentAdapter mAdapter;
    private TaskContract.Presenter mPresenter;
    private TaskPresenter mTaskPresenter;
    private ProgressDialog mProgressDialog;
    private EditText mEtKeyword;
    private FrameLayout mLoadingView;
    private FrameLayout mErrorView;
    private FrameLayout mContentView;
    private TextView mTvErrorMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTaskPresenter = new TaskPresenter(this);

        findViewById(R.id.btn_search).setOnClickListener(this);
        mEtKeyword = (EditText) findViewById(R.id.edit_text_key);

        mLoadingView = (FrameLayout) findViewById(R.id.loading_view);
        mErrorView = (FrameLayout) findViewById(R.id.error_view);
        mContentView = (FrameLayout) findViewById(R.id.content_view);
        mTvErrorMsg = (TextView) findViewById(R.id.tv_error_msg);
        mTvErrorMsg.setOnClickListener(this);

        RecyclerView recyclerView =  (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ContentAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void showContentView() {
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingView() {
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mContentView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(String msg) {
        mErrorView.setVisibility(View.VISIBLE);
        mTvErrorMsg.setText(msg);
        mLoadingView.setVisibility(View.GONE);
        mContentView.setVisibility(View.GONE);
    }

    @Override
    public String getSearchKeyword() {
        String keyword = mEtKeyword.getText().toString().trim();
        return "".equals(keyword) ? "a" : keyword;
    }

    @Override
    public void setUserInfos(List<UserInfo> userInfoList) {
        checkNotNull(userInfoList);
        mAdapter.refresh(userInfoList);
    }

    @Override
    public void showLoadingDialog(boolean show, int titleResId) {
        checkDialog();
        if (show) {
            mProgressDialog.setTitle(titleResId);
            mProgressDialog.show();
        } else {
            mProgressDialog.hide();
        }
    }

    private void checkDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.getInstance(this).showToast(msg);
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                hideSoftKeyBoard(this);
                mTaskPresenter.loadData(false);
                break;
            case R.id.tv_error_msg:
                hideSoftKeyBoard(this);
                mTaskPresenter.loadData(false);
                break;
        }

    }

    private void loadData() {
        UserApi userApi = ApiGeneratorImpl.getInstance().createApi(UserApi.class);
        //Call<BaseResponse<List<ItemBean>>> call = apiService.searchUsers("test");
        Call<BaseResponse<List<UserInfo>>> call = userApi.searchUsers("");
        call.enqueue(new Callback<BaseResponse<List<UserInfo>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<UserInfo>>> call, Response<BaseResponse<List<UserInfo>>> response) {
                if (response.isSuccessful()) {
                    mAdapter.refresh(response.body().getItems());
                } else {
                    ApiError error = ApiErrorUtils.parseError(response);
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<UserInfo>>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());

            }
        });
    }

    private class ContentAdapter extends RecyclerView.Adapter<VH> {
        private List<UserInfo> items;

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_info, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.bindData(items.get(position));
        }

        @Override
        public int getItemCount() {
            return null == items ? 0 :items.size();
        }

        public void refresh(List<UserInfo> items) {
            this.items =items;
            notifyDataSetChanged();
        }
    }

    public  class VH extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final TextView mTvRepos;
        private final ImageView mIvHead;

        public VH(View itemView) {
            super(itemView);
            mIvHead = (ImageView) itemView.findViewById(R.id.iv_head);
            mTvName =  (TextView) itemView.findViewById(R.id.tv_name);
            mTvRepos =  (TextView) itemView.findViewById(R.id.tv_repos);
        }

        public void bindData(UserInfo userInfo) {
            Glide.with(MainActivity.this)
                    .load(userInfo.getAvatar_url())
                    .transform(new GlideRoundTransform(MainActivity.this, 10))
                    .error(R.mipmap.default_avatar)
                    .into(mIvHead);

            mTvName.setText(userInfo.getLogin());
            mTvRepos.setText(userInfo.getRepos_url());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyDialog();
    }

    private void destroyDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    private void hideSoftKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}

package leevin.retrofitdemo.Task;

import android.content.Context;

import java.util.List;

import leevin.retrofitdemo.BasePresenter;
import leevin.retrofitdemo.BaseView;
import leevin.retrofitdemo.model.entity.UserInfo;

/**
 * Created by  Leevin
 * on 2016/9/12 ,20:12.
 */

public interface TaskContract {

    interface View extends BaseView<Presenter> {
        void showLoadingDialog(boolean show,int titleResId);
        void showToast(String msg);
        void showContentView();
        void showLoadingView();
        void showErrorView(String msg);
        String getSearchKeyword();
        void setUserInfos(List<UserInfo> listBaseResponse);
        Context getActivityContext();
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isRefresh);
    }
}

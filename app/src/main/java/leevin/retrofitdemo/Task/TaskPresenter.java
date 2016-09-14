package leevin.retrofitdemo.Task;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import leevin.retrofitdemo.R;
import leevin.retrofitdemo.domain.LoadUsersUseCase;
import leevin.retrofitdemo.model.entity.BaseResponse;
import leevin.retrofitdemo.model.entity.UserInfo;
import rx.Subscriber;

import static leevin.retrofitdemo.Utils.Preconditions.checkNotNull;

/**
 * Created by  Leevin
 * on 2016/9/12 ,20:31.
 */

public class TaskPresenter implements TaskContract.Presenter {

    private static final String TAG ="TaskPresenter";
    private final TaskContract.View mTasksView;
    private final LoadUsersUseCase mLoadUsersUseCase;

    public TaskPresenter(@NonNull TaskContract.View tasksView) {
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mLoadUsersUseCase = new LoadUsersUseCase();
        mTasksView.setPresenter(this);
    }

    @Override
    public void loadData(boolean isRefresh) {
        loadData(isRefresh, true);
    }

    private void loadData(boolean isRefresh, boolean showLoadingUI) {
//        mTasksView.showLoadingDialog(showLoadingUI, R.string.loading);
        mTasksView.showLoadingView();
        mLoadUsersUseCase.execute(getLoadUsersUseCaseSub(),mTasksView.getSearchKeyword());
    }

    private Subscriber getLoadUsersUseCaseSub() {
        return new Subscriber<BaseResponse<List<UserInfo>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                mTasksView.showLoadingDialog(false,0);
                mTasksView.showErrorView(getString(R.string.loading_failure_and_trying_again));
                Log.e(TAG, "onError: "+e.getMessage(),e );

            }

            @Override
            public void onNext(BaseResponse<List<UserInfo>> listBaseResponse) {
//                mTasksView.showLoadingDialog(false,0);
                Log.e(TAG, "onNext: "+listBaseResponse.toString());
                mTasksView.setUserInfos(listBaseResponse.getItems());
                mTasksView.showContentView();
            }
        };
    }

    private String getString(int stringId) {
        return mTasksView.getActivityContext().getString(stringId);
    }

    @Override
    public void start() {
        loadData(false);
    }
}

package leevin.retrofitdemo.model.repository;

import java.util.List;

import leevin.retrofitdemo.model.api.ApiGeneratorImpl;
import leevin.retrofitdemo.model.api.UserApi;
import leevin.retrofitdemo.model.entity.BaseResponse;
import leevin.retrofitdemo.model.entity.UserInfo;
import leevin.retrofitdemo.model.entity.UserRepos;
import rx.Observable;

/**
 * Created by  Leevin
 * on 2016/9/12 ,23:17.
 */

public class UserRepositoryImpl implements UserRepository {

    private UserApi mUserApi;

    public UserRepositoryImpl() {
        mUserApi = ApiGeneratorImpl.getInstance().createApi(UserApi.class);
    }

    @Override
    public Observable<BaseResponse<List<UserInfo>>> loadUsers(String params) {
        return mUserApi.loadUsers(params);
    }

    @Override
    public Observable<List<UserRepos>> loadUserRepos(String username) {
        return mUserApi.loadUserRepos(username);
    }
}

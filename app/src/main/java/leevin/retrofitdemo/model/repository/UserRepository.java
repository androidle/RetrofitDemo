package leevin.retrofitdemo.model.repository;

import java.util.List;

import leevin.retrofitdemo.model.entity.BaseResponse;
import leevin.retrofitdemo.model.entity.UserInfo;
import leevin.retrofitdemo.model.entity.UserRepos;
import rx.Observable;

/**
 * Created by  Leevin
 * on 2016/9/12 ,22:58.
 */

public interface UserRepository {

    Observable<BaseResponse<List<UserInfo>>> loadUsers(String params);

    Observable<List<UserRepos>> loadUserRepos(String username);
}

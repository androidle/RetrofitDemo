package leevin.retrofitdemo.model.api;

import java.util.List;

import leevin.retrofitdemo.model.entity.BaseResponse;
import leevin.retrofitdemo.model.entity.UserInfo;
import leevin.retrofitdemo.model.entity.UserRepos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by  Leevin
 * on 2016/9/9 ,21:43.
 */

public interface UserApi {
    @GET("search/users")
    Call<BaseResponse<List<UserInfo>>> searchUsers(@Query("q") String q);

    @GET("users/{name}/epos/")
    Call<List<UserRepos>> searchTest(@Part("name") String name);

    @GET("search/users")
    Observable<BaseResponse<List<UserInfo>>> loadUsers(@Query("q") String q);

    @GET("users/{name}/repos/")
    Observable<List<UserRepos>> loadUserRepos(@Part("name") String name);

}


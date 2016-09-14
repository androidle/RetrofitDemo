package leevin.retrofitdemo.domain;

import leevin.retrofitdemo.model.repository.UserRepository;
import leevin.retrofitdemo.model.repository.UserRepositoryImpl;
import rx.Observable;

/**
 * Created by  Leevin
 * on 2016/9/12 ,23:29.
 */

public class LoadUsersUseCase extends UseCase<String> {

    private UserRepository mUserRepository;

    public LoadUsersUseCase() {
        mUserRepository = new UserRepositoryImpl();
    }

    @Override
    protected Observable buildUseCaseObservable(String... q) {
        return mUserRepository.loadUsers(q[0]);
    }
}

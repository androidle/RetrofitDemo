package leevin.retrofitdemo.domain;


import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<Q extends Object> {

    public static final Scheduler UI_THREAD = AndroidSchedulers.mainThread();
    public static Scheduler EXECUTOR_THREAD = Schedulers.io();

    private Subscription subscription = Subscriptions.empty();

    protected UseCase() {
    }

    protected abstract Observable buildUseCaseObservable(Q ... q);

    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void execute(Subscriber UseCaseSubscriber , Q ... q) {
        this.subscription = this.buildUseCaseObservable(q)
                .subscribeOn(EXECUTOR_THREAD)
                .observeOn(UI_THREAD)
                .subscribe(UseCaseSubscriber);
    }

}

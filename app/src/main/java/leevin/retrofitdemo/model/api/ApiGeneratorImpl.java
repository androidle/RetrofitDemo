package leevin.retrofitdemo.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by  Leevin
 * on 2016/9/11 ,23:04.
 */

public class ApiGeneratorImpl implements ApiGenerator {

    private OkHttpClient mOkHttpClient;
    public static volatile ApiGeneratorImpl instance;

    private ApiGeneratorImpl() {
        initOkHttpClient();
    }

    public static ApiGeneratorImpl getInstance() {
        if (instance == null) {
            synchronized (ApiGeneratorImpl.class) {
                if (instance == null) {
                    instance = new ApiGeneratorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> T createApi(Class<T> tClass) {
        return  getRetrofit().create(tClass);
    }

    public Retrofit getRetrofit() {
        Retrofit retrofit = null;
        if (retrofit == null) {
              retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return retrofit;
    }

    private Gson getConfigGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }
    private  void initOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.connectTimeout(3, TimeUnit.SECONDS);
            builder.addInterceptor(getHttpLoggingInterceptor());
            mOkHttpClient = builder.build();
        }
    }

    private  HttpLoggingInterceptor getHttpLoggingInterceptor() {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override public void log(String message) {
//                Log.e(TAG, "OkHttp: ===:"+message);
//            }
//        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }





}

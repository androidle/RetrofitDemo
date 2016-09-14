package leevin.retrofitdemo.model.api;

/**
 * Created by  Leevin
 * on 2016/9/11 ,23:27.
 */
public interface ApiGenerator {
    String BASE_URL = "https://api.github.com/";
    <T> T createApi(Class<T> tClass);
}

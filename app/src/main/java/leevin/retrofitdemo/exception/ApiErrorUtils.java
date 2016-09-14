package leevin.retrofitdemo.exception;

import java.io.IOException;
import java.lang.annotation.Annotation;

import leevin.retrofitdemo.model.api.ApiGeneratorImpl;
import leevin.retrofitdemo.model.entity.ApiError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by  Leevin
 * on 2016/9/12 ,17:06.
 */

public class ApiErrorUtils {

    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                ApiGeneratorImpl.getInstance().getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}

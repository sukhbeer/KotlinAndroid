package pl.futuredev.popularmoviesudacitynd.service;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.futuredev.popularmoviesudacitynd.utils.UrlManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpConnector {

    private static Retrofit retrofit;

    private HttpConnector() {
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            return createRetrofit();
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    @NonNull
    private static Retrofit createRetrofit() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClientBuilder.addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

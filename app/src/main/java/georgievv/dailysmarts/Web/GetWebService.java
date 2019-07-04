package georgievv.dailysmarts.Web;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetWebService {
    private Retrofit retrofit;

    public GetWebService(String URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

package connectmodule.Service;

import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface UserService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    //        @Headers(value = {HEADER_API_VERSION})
    @GET("/users")
    public Observable<List<UserResponse>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

}

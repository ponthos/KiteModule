package connectmodule.Model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import javax.inject.Inject;

import connectmodule.Contract.UserContract;
import connectmodule.Service.UserService;
import io.reactivex.Observable;

@ActivityScope
public class UserModel extends BaseModel implements UserContract.Model{

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<List<UserResponse>> getUsers(int lastIdQueried, boolean update) {
        return (Observable<List<UserResponse>>) mRepositoryManager.obtainRetrofitService(UserService.class)
                .getUsers(1,0);
    }
}
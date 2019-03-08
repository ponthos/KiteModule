package connectmodule.Component;


import android.app.Activity;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import connectmodule.Module.UserModule;
import dagger.Component;


@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(Activity activity);
}

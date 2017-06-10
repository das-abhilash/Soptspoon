package in.zollet.abhilashdas.hellochat.view.viewmodel.contract;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by mayursharma on 2/2/17.
 */

public interface LifeCycle {

    interface View{
        Context getActivityContext();
        void showProgress();
        void hideProgress();
        void showError(Exception e);
    }

    interface ViewModel{
        void onViewResumed();
        void onViewCreated(@NonNull LifeCycle.View viewCallback);
        void onViewStart(@NonNull LifeCycle.View viewCallback);
        void onViewStop();
        void onViewDestroyed();
        void onDestroy();
    }

}

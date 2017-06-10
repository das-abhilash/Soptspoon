package in.zollet.abhilashdas.hellochat.view.viewmodel.contract;

/**
 * Created by abhilashdas on 26/05/17.
 */

public interface ViewModelContract {

    interface View extends LifeCycle.View {
        Object getData();
    }

    interface ViewModel extends LifeCycle.ViewModel {
    }
}

package com.example.urbandictonaryapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.urbandictonaryapp.model.Definition;
import com.example.urbandictonaryapp.model.UrbanResponse;
import com.example.urbandictonaryapp.repository.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class SearchWordViewModel extends AndroidViewModel {

    private MutableLiveData<List<Definition>> definitions = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    private Repository repo = Repository.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public SearchWordViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDefinitionsObservable(String term) {
        repo.getDefinitions(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrbanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Show Progress Bar
                        showProgressBar.setValue(true);
                    }

                    @Override
                    public void onNext(UrbanResponse urbanResponse) {
                        // Receive data here
                        definitions.setValue(urbanResponse.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Something went wrong
                        error.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Hide Progress Bar
                        showProgressBar.setValue(false);
                    }
                });
    }


    public LiveData<List<Definition>> getDefinitions() {
        Log.d(TAG, "getDefinitions: " + definitions);
        return definitions;
    }

    public LiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }

    public LiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}

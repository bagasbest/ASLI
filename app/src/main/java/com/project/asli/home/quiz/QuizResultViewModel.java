package com.project.asli.home.quiz;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class QuizResultViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<QuizModel>> lisQuiz = new MutableLiveData<>();
    final ArrayList<QuizModel> quizModelArrayList = new ArrayList<>();

    private static final String TAG = QuizResultViewModel.class.getSimpleName();

    public void setLisQuiz(String collection) {
        quizModelArrayList.clear();

        try {
            FirebaseFirestore
                    .getInstance()
                    .collection(collection)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                QuizModel model = new QuizModel();

                                model.setQuestion("" + document.get("question"));
                                model.setAnswer("" + document.get("answer"));

                                quizModelArrayList.add(model);
                            }
                            lisQuiz.postValue(quizModelArrayList);
                        } else {
                            Log.e(TAG, task.toString());
                        }
                    });
        } catch (Exception error) {
            error.printStackTrace();
        }
    }


    public LiveData<ArrayList<QuizModel>> getListQuiz() {
        return lisQuiz;
    }


}

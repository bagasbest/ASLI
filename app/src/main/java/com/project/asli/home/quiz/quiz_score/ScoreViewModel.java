package com.project.asli.home.quiz.quiz_score;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class ScoreViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ScoreModel>> listScore = new MutableLiveData<>();
    final ArrayList<ScoreModel> scoreModelArrayList = new ArrayList<>();

    private static final String TAG = ScoreViewModel.class.getSimpleName();

    public void setListScore(String quizType) {
        scoreModelArrayList.clear();

        try {
            FirebaseFirestore
                    .getInstance()
                    .collection("result")
                    .whereEqualTo("quizType", quizType)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ScoreModel model = new ScoreModel();

                                model.setName("" + document.get("name"));
                                model.setQuizType("" + document.get("quizType"));
                                model.setScore(document.getDouble("score"));
                                model.setUserId("" + document.get("userId"));
                                model.setNim("" + document.get("nim"));

                                scoreModelArrayList.add(model);
                            }
                            listScore.postValue(scoreModelArrayList);
                        } else {
                            Log.e(TAG, task.toString());
                        }
                    });
        } catch (Exception error) {
            error.printStackTrace();
        }
    }


    public LiveData<ArrayList<ScoreModel>> getListScore() {
        return listScore;
    }
}

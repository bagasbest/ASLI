package com.project.asli.home.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizModel implements Parcelable {

    private String questionId;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;

    public QuizModel() {

    }

    protected QuizModel(Parcel in) {
        questionId = in.readString();
        question = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        d = in.readString();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionId);
        dest.writeString(question);
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(d);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuizModel> CREATOR = new Creator<QuizModel>() {
        @Override
        public QuizModel createFromParcel(Parcel in) {
            return new QuizModel(in);
        }

        @Override
        public QuizModel[] newArray(int size) {
            return new QuizModel[size];
        }
    };

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

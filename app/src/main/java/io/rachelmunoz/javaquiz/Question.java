package io.rachelmunoz.javaquiz;

/**
 * Created by rachelmunoz on 6/29/17.
 */

public class Question {
    private int mTextResId;
    private boolean mIsAnswerTrue;

    public Question(int questionText, boolean isAnswerTrue){
        mTextResId = questionText;
        mIsAnswerTrue = isAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }
}

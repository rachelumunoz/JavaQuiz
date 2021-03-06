package io.rachelmunoz.javaquiz;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private final String CURRENT_INDEX = "io.rachelmunoz.android.javaquiz.current_index";

    private TextView mQuestionTextView;
    private ImageButton mPreviousButton;
    private ImageButton mNextButton;
    private Button mTrueButton;
    private Button mFalseButton;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_one, true),
        new Question(R.string.question_two, false),
        new Question(R.string.question_three, false),
    };

    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCurrentIndex == 0){
                    Toast.makeText(QuizActivity.this, "Hit Next", Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentIndex = mCurrentIndex - 1;
                    updateQuestion();
                }
            }
        });
    }

    private void updateQuestion(){
        int questionTextId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionTextId);
    }

    private void checkAnswer(boolean answerGuessed){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;

        if (answerGuessed == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
    }
}

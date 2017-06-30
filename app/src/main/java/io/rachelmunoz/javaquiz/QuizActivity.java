package io.rachelmunoz.javaquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CHEAT = 0;

    private TextView mQuestionTextView;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private boolean mUserCheated;

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

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mUserCheated = false;

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

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // intent - putExtra - question's answer
                boolean questionAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, questionAnswer);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
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

        if (mUserCheated){
            messageResId = R.string.judgement_toast;
        } else {
            if (answerGuessed == answerIsTrue){
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }


        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode != REQUEST_CODE_CHEAT){
            return;
        }

        if ( data != null){
            mUserCheated = CheatActivity.didUserCheat(data);
        }


    }
}

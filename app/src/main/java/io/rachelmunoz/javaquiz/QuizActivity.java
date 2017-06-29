package io.rachelmunoz.javaquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private Question mCurrentQuestion = new Question(R.string.question_one, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int questionTextId = mCurrentQuestion.getTextResId();
        mQuestionTextView.setText(questionTextId);

    }
}

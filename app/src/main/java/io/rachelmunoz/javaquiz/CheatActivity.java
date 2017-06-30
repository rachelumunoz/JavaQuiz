package io.rachelmunoz.javaquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "io.rachelmunoz.android.javaquiz.answer_is_true";
    private static final String USER_CHEATED = "io.rachelmunoz.android.javaquiz.user_cheated";

    private boolean mAnswerTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mAnswerTrue){
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                sendingBack(true);
            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean isAnswerTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerTrue);
        return intent;
    }

    public static boolean didUserCheat(Intent intent){
        return intent.getBooleanExtra(USER_CHEATED, false);
    }


    private void sendingBack(boolean userCheated){
        Intent data = new Intent();
        data.putExtra(USER_CHEATED, userCheated);
        setResult(RESULT_OK, data);
    }
}

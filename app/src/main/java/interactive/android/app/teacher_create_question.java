package interactive.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import interactive.android.app.ui.AccountDetails;

public class teacher_create_question extends AppCompatActivity {
    Button submit_question;
    EditText quizTitleEdt,answer1Edt,answer2Edt,answer3Edt,answer4Edt;
    MultiAutoCompleteTextView questionMACT;
    Spinner listOfSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_question);
        submit_question = findViewById(R.id.submit_question);
        quizTitleEdt = findViewById(R.id.quizTitle);
                answer1Edt = findViewById(R.id.answer1);
        answer2Edt = findViewById(R.id.answer2);
                answer3Edt= findViewById(R.id.answer3);
        answer4Edt = findViewById(R.id.answer4);
        questionMACT = findViewById(R.id.question);
//        listOfSubjects = findViewById(R.id.listOfSubjects);

//        submit_question.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String quiz_title, question,ans1,ans2,ans3,ans4,subject,start_date,end_date;
//               quiz_title = String.valueOf(quizTitleEdt.getText());
//               question = String.valueOf(questionMACT.getText());
//               ans1 = String.valueOf(answer1Edt.getText());
//               ans2 =String.valueOf(answer2Edt.getText());
//               ans3= String.valueOf(answer3Edt.getText());
//               ans4 = String.valueOf(answer4Edt.getText());
////               subject = String.valueOf(listOfSubjects.getTe)
//
//                if(!email.equals("") && !password.equals("")){
//
//                    Handler handler = new Handler(Looper.getMainLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            String[] field = new String[11];
//                            field[0] = "account_id";
//                            field[1] = "subject_id";
//                            field[2] = "quiz_title";
//                            field[3] = "question";
//                            field[4] = "ans1";
//                            field[5] = "ans2";
//                            field[6] = "ans3";
//                            field[7] = "ans4";
//                            field[8] = "subject";
//                            field[9] = "start_date";
//                            field[10] = "end_date";
//                            String[] data = new String[11];
//                            data[0] = String.valueOf(Login.prefs.getString("account_id",null));
//                            data[1] = password;
//                            data[2] = quiz_title;
//                            data[3] = question;
//                            data[4] = ans1;
//                            data[5] = ans2;
//                            data[6] = ans3;
//                            data[7] = ans4;
//                            data[8] = email;
//                            data[9] = password;
//                            data[10] = password;
//                            PutData putData = new PutData("https://quizapp.fuxdevs-ph.xyz/quiz.php", "POST", field, data);
//                            if (putData.startPut()) {
//                                if (putData.onComplete()) {
//                                    String result = putData.getResult();
//                                    Log.d("resulta",result);
//                                }
//                            }
//                        }
//                    });
//                }else{
//                    Toast.makeText(teacher_create_question.this, "Email and Password Empty!", Toast.LENGTH_SHORT).show();
//                }
//            }
//            }
//        });
    }
}
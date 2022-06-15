package interactive.android.app;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class teacher_create_question extends AppCompatActivity {
    Button submit_question;
    EditText quizTitleEdt,answer1Edt,answer2Edt,answer3Edt,answer4Edt, startDates, endDates;
    MultiAutoCompleteTextView questionMACT;
    Spinner listOfSubjects;
    DatePickerDialog picker;

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
        startDates = (EditText) findViewById(R.id.startDate);
        endDates = (EditText) findViewById(R.id.endDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            startDates.setInputType(InputType.TYPE_NULL);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            endDates.setInputType(InputType.TYPE_NULL);
        }
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


        startDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(teacher_create_question.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDates.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        endDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(teacher_create_question.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDates.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

}
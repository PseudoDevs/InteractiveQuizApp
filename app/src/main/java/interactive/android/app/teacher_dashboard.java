package interactive.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

import interactive.android.app.ui.AccountDetails;

public class teacher_dashboard extends AppCompatActivity {
    MaterialButton add_new_subject,add_student_to_subject,create_quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        add_new_subject = findViewById(R.id.add_new_subject);
        add_student_to_subject = findViewById(R.id.add_student_to_subject);
        create_quiz = findViewById(R.id.create_quiz);

        create_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teacher_dashboard.this,teacher_create_question.class);
                startActivity(intent);
            }
        });

        add_student_to_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teacher_dashboard.this,teacher_add_student_to_subject.class);
                startActivity(intent);
            }
        });
        add_new_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }

    public void showDialog(){
        Dialog dialog = new Dialog(teacher_dashboard.this);
        dialog.setContentView(R.layout.add_subject_popup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        Button subject_submit = (Button) dialog.findViewById(R.id.submit_subject);
        TextInputEditText subjectEdt = (TextInputEditText) dialog.findViewById(R.id.subjectEdt);

        subject_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(teacher_dashboard.this, "dwa", Toast.LENGTH_SHORT).show();
                addNewSubject(String.valueOf(subjectEdt.getText()), String.valueOf(Login.prefs.getString("account_id",null)));
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void addNewSubject(String subject,String account_id){

        if(!subject.equals("")){

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[2];
                    field[0] = "account_id";
                    field[1] = "subject";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = account_id;
                    data[1] = subject;
                    PutData putData = new PutData("https://quizapp.fuxdevs-ph.xyz/add_new_subject.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            Log.d("resulta",result);
                        }
                    }
                }
            });
        }else{
            Toast.makeText(teacher_dashboard.this, "Subject Empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
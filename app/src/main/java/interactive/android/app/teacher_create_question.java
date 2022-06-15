package interactive.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import static android.R.layout.simple_spinner_item;

import interactive.android.app.ui.AccountDetails;

public class teacher_create_question extends AppCompatActivity {

    Button submit_question;
    EditText quizTitleEdt,answer1Edt,answer2Edt,answer3Edt,answer4Edt,editTextDate3,editTextDate4,correct_answerEdt, startDates, endDates;;
    MultiAutoCompleteTextView questionMACT;
    Spinner listOfSubjects;


    private ArrayList<SubjectModel> subjectModelArrayList;
    private ArrayList<String> subjects = new ArrayList<String>();

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
        listOfSubjects = findViewById(R.id.listOfSubjects);
        editTextDate3 = findViewById(R.id.editTextDate3);
        editTextDate4 = findViewById(R.id.editTextDate4);
        correct_answerEdt =findViewById(R.id.correct_answer);
        startDates = findViewById(R.id.startDate);
        endDates = findViewById(R.id.endDate);
        retrieveJSON();

        submit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String account_id,subject_id,quiz_title, question, stats_quiz,ans1,ans2,ans3,ans4,subject,start_date,end_date,correct_answer;
                account_id = String.valueOf(Login.prefs.getString("account_id",null));
                subject_id = String.valueOf(subjectModelArrayList.get(listOfSubjects.getSelectedItemPosition()).getSubject_id());
                quiz_title = String.valueOf(quizTitleEdt.getText());
                question = String.valueOf(questionMACT.getText());
                stats_quiz = "active";
                ans1 = String.valueOf(answer1Edt.getText());
                ans2 = String.valueOf(answer2Edt.getText());
                ans3 = String.valueOf(answer3Edt.getText());
                ans4 = String.valueOf(answer4Edt.getText());
                subject = String.valueOf(listOfSubjects.getSelectedItem().toString());
                start_date = String.valueOf(editTextDate3.getText());
                end_date = String.valueOf(editTextDate4.getText());
                correct_answer = String.valueOf(correct_answerEdt.getText());

                if(!quiz_title.equals("") && !question.equals("")){

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[13];
                            field[0] = "account_id";
                            field[1] = "subject_id";
                            field[2] = "quiz_title";
                            field[3] = "question";
                            field[4] = "stats_quiz";
                            field[5] = "ans1";
                            field[6] = "ans2";
                            field[7] = "ans3";
                            field[8] = "ans4";
                            field[9] = "subject";
                            field[10] = "start_date";
                            field[11] = "end_date";
                            field[12] = "correct_answer";
                            String[] data = new String[13];
                            data[0] = account_id;
                            data[1] = subject_id;
                            data[2] = quiz_title;
                            data[3] = question;
                            data[4] = stats_quiz;
                            data[5] = ans1;
                            data[6] = ans2;
                            data[7] = ans3;
                            data[8] = ans4;
                            data[9] = subject;
                            data[10] = start_date;
                            data[11] = end_date;
                            data[12] = correct_answer;
                            PutData putData = new PutData("https://quizapp.fuxdevs-ph.xyz/quiz.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    Toast.makeText(teacher_create_question.this, result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(teacher_create_question.this, "Email and Password Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void retrieveJSON() {

//        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://quizapp.fuxdevs-ph.xyz/fetch_subject.php?act_id="+Login.prefs.getString("account_id",null),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            subjectModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("subjects");

                            for (int i = 0; i < dataArray.length(); i++) {

                                SubjectModel subjectModel = new SubjectModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                subjectModel.setSubject(dataobj.getString("subject_name"));
                                subjectModel.setSubject_id(dataobj.getString("s_id"));

                                subjectModelArrayList.add(subjectModel);

                            }

                            for (int i = 0; i < subjectModelArrayList.size(); i++){
                                subjects.add(subjectModelArrayList.get(i).getSubject().toString());
                            }



                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(teacher_create_question.this, simple_spinner_item, subjects);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            listOfSubjects.setAdapter(spinnerArrayAdapter);
//                                removeSimpleProgressDialog();

                            Log.d("resultaa",String.valueOf(subjects.size()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
}
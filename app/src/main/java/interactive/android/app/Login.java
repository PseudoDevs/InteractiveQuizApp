package interactive.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import interactive.android.app.ui.AccountDetails;

public class Login extends AppCompatActivity {
    MaterialButton loginBtn;
    TextInputEditText passwordEdt,emailEdt;
    ArrayList<AccountDetails> accountDetails;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordEdt = (TextInputEditText) findViewById(R.id.passwordEdt);
        emailEdt = (TextInputEditText)findViewById(R.id.emailEdt);
        loginBtn = (MaterialButton)findViewById(R.id.loginBtn);
        editor = getSharedPreferences("StoringData", MODE_PRIVATE).edit();
        prefs = getSharedPreferences("StoringData", MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password;
                email = String.valueOf(emailEdt.getText());
                password = String.valueOf(passwordEdt.getText());

                if(!email.equals("") && !password.equals("")){

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "password";
                        String[] data = new String[2];
                        data[0] = email;
                        data[1] = password;
                        PutData putData = new PutData("https://quizapp.fuxdevs-ph.xyz/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();

                                Log.d("resulta",result);

                                try {
                                    final JSONObject obj = new JSONObject(result);
                                    final JSONArray resultdata = obj.getJSONArray("account");
                                    final JSONObject account = resultdata.getJSONObject(0);
                                    String account_id = account.getString("id");
                                    String account_fname = account.getString("fname");
                                    String account_role = account.getString("role");
                                    accountDetails = new ArrayList<>();
                                    accountDetails.add(new AccountDetails(
                                            account_id,
                                            account_fname,
                                            account_role
                                    ));

                                    if(account_role.equals("student")){
                                        Login.editor.putString("account_id",account_id).commit();
                                        Intent intent = new Intent(Login.this,MainActivity.class);
                                        startActivity(intent);
                                    }else if(account_role.equals("teacher")){
                                        Login.editor.putString("account_id",account_id).commit();
                                        Intent intent = new Intent(Login.this,teacher_dashboard.class);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    }
                });
                }else{
                    Toast.makeText(Login.this, "Email and Password Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
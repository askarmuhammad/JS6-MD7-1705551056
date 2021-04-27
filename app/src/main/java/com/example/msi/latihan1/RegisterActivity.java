package com.example.msi.latihan1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.msi.latihan1.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$"
    );
    private ImageView mDisplayDate;
    private EditText email;
    private TextView mDisplayDate2;
    private TextView gender;
    private EditText password;
    RadioButton pria,wanita;
    private TextInputLayout textInputLayoutpass;
    private TextInputLayout textInputLayoutconfirm;
    RadioGroup radioGroup;
    Button btn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDisplayDate = (ImageView) findViewById(R.id.date);
        mDisplayDate2 = (TextView) findViewById(R.id.lahir);
        gender = (TextView) findViewById(R.id.gender);
        email = (EditText) findViewById(R.id.username);
        textInputLayoutpass = findViewById(R.id.textInputLayout1);
        radioGroup = (RadioGroup) findViewById(R.id.rgroup);
        password = findViewById(R.id.password);
        pria = findViewById(R.id.pria);
        wanita = findViewById(R.id.wanita);
        btn = (Button) findViewById(R.id.register);
        textInputLayoutpass = (TextInputLayout) findViewById(R.id.textInputLayout1);
        textInputLayoutconfirm = (TextInputLayout) findViewById(R.id.textInputLayout2);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + dayOfMonth + "-" + month + "-" + year);
                month = month + 1;

                String date = dayOfMonth + "-" + month + "-" + year;
                mDisplayDate2.setText(date);
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

            }
        });
    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validatePass() {
        String pass = textInputLayoutpass.getEditText().getText().toString().trim();

        if (pass.isEmpty()) {
            textInputLayoutpass.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
            textInputLayoutpass.setError("password terlalu lemah");
            return false;
        } else {
            textInputLayoutpass.setError(null);
            return true;
        }
    }
    private boolean validatePassConfirm() {
        String passConfirm = textInputLayoutconfirm.getEditText().getText().toString().trim();

        if (passConfirm.isEmpty()) {
            textInputLayoutconfirm.setError("Field can't be empty");
            return false;
        } else if (!textInputLayoutconfirm.getEditText().getText().toString().equals(textInputLayoutpass.getEditText().getText().toString())) {
            textInputLayoutconfirm.setError("Password not same");
            return false;
        } else {
            textInputLayoutconfirm.setError(null);
            return true;
        }
    }
    private boolean validateDate(){
        String date = mDisplayDate2.getText().toString();

        if(date.isEmpty()){
            mDisplayDate2.setError("Field can't be empty");
            return false;
        }
        else{
            mDisplayDate2.setError(null);
            return true;
        }
    }
    public void validation() {
        int isSelected = radioGroup.getCheckedRadioButtonId();

        if (isSelected == -1) {
            Toast.makeText(RegisterActivity.this, "pilih salah satu gender", Toast.LENGTH_LONG).show();

            return;
        }
        if (!validateEmail() | !validatePass() | !validatePassConfirm()) {
            return;
        }
        if(!validateDate()){
            return;
        }
        String input = "Email: " + email.getEditableText().getFilters().toString();
        input += "\n";
        input += "Password: " + textInputLayoutpass.getEditText().getText().toString();
        input += "\n";
        input += "Confirm Password: " + textInputLayoutconfirm.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        register();
        finish();


    }

    private void register(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "Response insert data : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "Error insert data : " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                params.put("date",mDisplayDate2.getText().toString());
                params.put("gender",gender.getText().toString());
                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.pria:
                if (checked)
                    gender.setText("pria");
                    break;
            case R.id.wanita:
                if (checked)
                    gender.setText("wanita");
                    break;
        }
    }
}
package com.example.firebasetest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomDialog extends Dialog {
    private Context context;
    Button btnOk,btnCancel;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3;
    int a;

    FirebaseDatabase database;
    DatabaseReference myRef;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        radioGroup = findViewById(R.id.radioGroup);
        //rb1 = findViewById(R.id.rb_a);
        //rb2 = findViewById(R.id.rb_b);
        //rb3 = findViewById(R.id.rb_c);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_a:System.out.println("a");
                    a++;
                    myRef.child("message").child("-MRc23CGSfnzyBkPnZYB").setValue(2);
                    break;
                    case R.id.rb_b:System.out.println("b");break;
                    case R.id.rb_c:System.out.println("c");break;
                }
                //System.out.println(selectedId);
                dismiss();
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dismiss();
            }
        });
    }


}

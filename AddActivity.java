package four.eight.lifestats;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";

    Button create;
    EditText editText;
    EditText num;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editText = (EditText) findViewById(R.id.editText3) ;
        num = (EditText) findViewById(R.id.editText5);
        create = (Button) findViewById(R.id.createButton1);
        mDatabaseHelper = new DatabaseHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String number = num.getText().toString();
                int finalVal = Integer.parseInt(number);

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                if (editText.length() != 0) {
                    AddData(newEntry, finalVal);
                    System.out.println(newEntry);
                    editText.setText("");
                    num.setText("");
                    startActivity(intent);
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }


    public void AddData(String newEntry, int finalVal){
        boolean insertData = mDatabaseHelper.addData(newEntry, finalVal);

        if (insertData) {
            toastMessage("Data Succesfully Inserted");
        }
        else{
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

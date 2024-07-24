package com.asn8.sqlitetestflightone;

import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText customerNameEditText,ageNumberEditText;
    MaterialButton viewButton,addButton;
    Switch customerActiveSwitch;
    ListView lv_customerView;
    CustomerModel customerModel;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerNameEditText = findViewById(R.id.customerNameEditText);
        ageNumberEditText = findViewById(R.id.ageNumberEditText);
        viewButton = findViewById(R.id.viewButton);
        addButton = findViewById(R.id.addButton);
        customerActiveSwitch = findViewById(R.id.customerActiveSwitch);
        lv_customerView = findViewById(R.id.lv_customerList);


        showCostumers();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    customerModel = new CustomerModel(-1,customerNameEditText.getText().toString(),
                            Integer.parseInt(ageNumberEditText.getText().toString()),customerActiveSwitch.isChecked());
                    //Toast.makeText(MainActivity.this , customerModel.toString(),Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error "+ e ,Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1 , "error" , 0 , false );
                }
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean heloo = dataBaseHelper.addOne(customerModel);
                String heyya = new Boolean(heloo).toString();
                Toast.makeText(MainActivity.this,heyya,Toast.LENGTH_LONG).show();
                showCostumers();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCostumers();
            }
        });

       lv_customerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel customerCLicked = (CustomerModel) adapterView.getItemAtPosition(i);
                dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteOne(customerCLicked);
                showCostumers();
            }
        });
    }

    private void showCostumers(){
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone(customerModel));
        lv_customerView.setAdapter(customerArrayAdapter);
    }

}
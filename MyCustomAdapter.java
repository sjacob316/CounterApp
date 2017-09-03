package four.eight.lifestats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Selwyn Jacob on 8/14/2017.
 */

public class MyCustomAdapter extends ArrayAdapter<String> {

    //INITIALIZATIONS

    ArrayList<String> names;
    ArrayList<Integer> numbers;
    Context c;
    LayoutInflater inflater;
    DatabaseHelper mDatabaseHelper;


    MyCustomAdapter(Context context, ArrayList<String> names, ArrayList<Integer> numbers){
        super(context, R.layout.display_row, names);

        this.c = context;
        this.names = names;
        this.numbers = numbers;
        mDatabaseHelper = new DatabaseHelper(getContext());
    }

    public class ViewHolder{
        TextView namesTV;
        TextView numbersTV;
        Button addBut;
        Button subBut;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.display_row, null);
        }
        final ViewHolder holder = new ViewHolder();

        holder.namesTV = (TextView) convertView.findViewById(R.id.counterNameDisplay);
        holder.numbersTV = (TextView) convertView.findViewById(R.id.counterDisplay);
        holder.addBut = (Button) convertView.findViewById(R.id.addButton);
        holder.subBut = (Button) convertView.findViewById(R.id.subtractButton);

        holder.namesTV.setText(names.get(position));
        holder.numbersTV.setText(String.valueOf(numbers.get(position)));

        final int[] initialVal = {numbers.get(position)};

        holder.addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialVal[0]++;
                holder.numbersTV.setText(String.valueOf(initialVal[0]));
                int id = position + 1;
                String oneValue = holder.numbersTV.getText().toString();
                int value = Integer.parseInt(oneValue);
                mDatabaseHelper.updateNumber(id, value);
            }
        });

        holder.subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialVal[0]--;
                holder.numbersTV.setText(String.valueOf(initialVal[0]));
                int id = position + 1;
                String oneValue = holder.numbersTV.getText().toString();
                int value = Integer.parseInt(oneValue);
                mDatabaseHelper.updateNumber(id, value);
            }
        });
        return convertView;
    }
}

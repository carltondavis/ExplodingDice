package com.dasixes.explodingdice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//TODO: Add raw data as pop-up
//TODO: Make outcome numbers bigger
//TODO: Make center slider prettier
//TODO: Add copyright info
//TODO: Make buttons look like dice


public class MainActivity extends ActionBarActivity {
    Dice dice = new Dice();
    NumberPicker np;
    private CustomDiceOperations customdiceDBoperation;
    private List dicepools = new ArrayList();
    private List customdice = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        np = (NumberPicker) findViewById(R.id.numberPicker);

        np.setMinValue(1);
        np.setMaxValue(100);
        np.setWrapSelectorWheel(false);


        final Button postEdge= (Button) findViewById(R.id.PostEdge);

        postEdge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Grey out and stop working after first use
                dice.postEdge();
                updateoutcome();
            }
        });

        final Button die1= (Button) findViewById(R.id.Die1);


        die1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die1);
            }
        });

        die1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die1);
                return true;
            }
        });

        final Button die2= (Button) findViewById(R.id.Die2);

        die2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die2);
            }
        });

        die2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die2);
                return true;
            }
        });

        final Button die3= (Button) findViewById(R.id.Die3);

        die3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die3);
            }
        });

        die3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die3);
                return true;
            }
        });

        final Button die4= (Button) findViewById(R.id.Die4);

        die4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die4);
            }
        });

        die4.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die4);
                return true;
            }
        });

        final Button die5= (Button) findViewById(R.id.Die5);

        die5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die5);
            }
        });

        die5.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die5);
                return true;
            }
        });

        final Button die6= (Button) findViewById(R.id.Die6);

        die6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die6);
            }
        });

        die6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die6);
                return true;
            }
        });

        final Button die7= (Button) findViewById(R.id.Die7);

        die7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die7);
            }
        });

        die7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die7);
                return true;
            }
        });

        final Button die8= (Button) findViewById(R.id.Die8);

        die8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die8);
            }
        });

        die8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die8);
                return true;
            }
        });

        final Button die9= (Button) findViewById(R.id.Die9);

        die9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die9);
            }
        });

        die9.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die9);
                return true;
            }
        });

        final Button die10= (Button) findViewById(R.id.Die10);

        die10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick(die10);
            }
        });

        die10.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick(die10);
                return true;
            }
        });


        customdiceDBoperation = new CustomDiceOperations(this);
        customdiceDBoperation.open();

        customdice= customdiceDBoperation.getAllCustomDice();
//        CustomDice dicepool=customdiceDBoperation.addDice(0,0);
        //customdiceDBoperation.deleteDice(dicepool);
//        Toast.makeText(this, "The DB has " +String.valueOf(customdice.size()) + " entries.", Toast.LENGTH_LONG).show();
        if(customdice.size()!=11) {
            resetDice();
        }
        loadDice();
//        values        die1.setText(String.valueOf(values));
        //values.get(0).toString();

        //Configure if not initialized.


    }



    private void shortclick(Button dieButton)
    {//Roll Dice
        //Is it pre-edged?



        CheckBox checkPreEdge = (CheckBox) findViewById(R.id.checkPreEdge);

        dice.rollDice(Integer.valueOf((dieButton.getText().toString())), checkPreEdge.isChecked());
        updateoutcome();
        /*if(checkPreEdge.isChecked()) {
            Toast.makeText(this, "Rolled " + (dieButton.getText().toString()) + "exploding dice.", Toast.LENGTH_LONG).show();
        }
        {
            Toast.makeText(this, "Rolled " + (dieButton.getText().toString()) + "dice.", Toast.LENGTH_LONG).show();
        }*/
        checkPreEdge.setChecked(false);
    }

    private void longclick(Button dieButton)
    {//Edit Number

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        dieButton.setText(String.valueOf(np.getValue()));

        CustomDice diepool= (CustomDice) customdice.get(dicepools.indexOf(dieButton));
        diepool.setAmount(np.getValue());
        diepool = customdiceDBoperation.updateDice(diepool);
        customdice.set(dicepools.indexOf(dieButton),diepool);

    }

    private void updateoutcome(){
        TextView Hits = (TextView) findViewById(R.id.textHits);
        TextView Ones = (TextView) findViewById(R.id.textOnes);
        TextView Sixes = (TextView) findViewById(R.id.textSixes);
        TextView Total = (TextView) findViewById(R.id.textTotal);

        Hits.setText(String.valueOf(dice.Hits));
        Ones.setText(String.valueOf(dice.Ones));
        Sixes.setText(String.valueOf(dice.Sixes));
        Total.setText(String.valueOf(dice.Sum));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void loadDice(){
        CustomDice diepool = (CustomDice) customdice.get(0);

        diepool= (CustomDice) customdice.get(0);
        final Button die1= (Button) findViewById(R.id.Die1);
        die1.setText(diepool.getAmount().toString());
        dicepools.add(0,die1);
        diepool= (CustomDice) customdice.get(1);
        final Button die2= (Button) findViewById(R.id.Die2);
        die2.setText(diepool.getAmount().toString());
        dicepools.add(1,die2);
        diepool= (CustomDice) customdice.get(2);
        final Button die3= (Button) findViewById(R.id.Die3);
        die3.setText(diepool.getAmount().toString());
        dicepools.add(2,die3);
        diepool= (CustomDice) customdice.get(3);
        final Button die4= (Button) findViewById(R.id.Die4);
        die4.setText(diepool.getAmount().toString());
        dicepools.add(3,die4);
        diepool= (CustomDice) customdice.get(4);
        final Button die5= (Button) findViewById(R.id.Die5);
        die5.setText(diepool.getAmount().toString());
        dicepools.add(4,die5);
        diepool= (CustomDice) customdice.get(5);
        final Button die6= (Button) findViewById(R.id.Die6);
        die6.setText(diepool.getAmount().toString());
        dicepools.add(5,die6);
        diepool= (CustomDice) customdice.get(6);
        final Button die7= (Button) findViewById(R.id.Die7);
        die7.setText(diepool.getAmount().toString());
        dicepools.add(6,die7);
        diepool= (CustomDice) customdice.get(7);
        final Button die8= (Button) findViewById(R.id.Die8);
        die8.setText(diepool.getAmount().toString());
        dicepools.add(7,die8);
        diepool= (CustomDice) customdice.get(8);
        final Button die9= (Button) findViewById(R.id.Die9);
        die9.setText(diepool.getAmount().toString());
        dicepools.add(8,die9);
        diepool= (CustomDice) customdice.get(9);
        final Button die10= (Button) findViewById(R.id.Die10);
        die10.setText(diepool.getAmount().toString());
        dicepools.add(9,die10);
        diepool= (CustomDice) customdice.get(10);
        np.setValue(diepool.getAmount());
    }
    public void resetDice(){
        Toast.makeText(this,"Long press a button to store dice there.", Toast.LENGTH_SHORT).show();
        customdiceDBoperation = new CustomDiceOperations(this);
        customdiceDBoperation.open();

        customdice= customdiceDBoperation.getAllCustomDice();
        for(int i=0;i<customdice.size();i++){
            customdiceDBoperation.deleteDice((CustomDice) customdice.get(i));
        }

        customdiceDBoperation.addDice(0,1);
        customdiceDBoperation.addDice(1,3);
        for(int i=2;i<10;++i){
            customdiceDBoperation.addDice(i,(i-1)*5);
        }
        customdiceDBoperation.addDice(10,0);
        customdice= customdiceDBoperation.getAllCustomDice();

        //      Toast.makeText(this, "The DB has " +String.valueOf(customdice.size()) + " entries.", Toast.LENGTH_LONG).show();

        //Check if Configured;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_reset:
                resetDice();
                loadDice();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

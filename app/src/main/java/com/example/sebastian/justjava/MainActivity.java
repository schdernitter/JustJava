package com.example.sebastian.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCoffees = 0;

    public void submitOrder(View view) {
        // displayMessage(OrderSummary(hasCream(), hasChocolate(), getUsername()));
        composeEmail("Yoloswag","Order summary");
    }
    public void composeEmail(String text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public boolean hasCream() {
        CheckBox mycheckbox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = mycheckbox.isChecked();
        return hasWhippedCream;
    }

    public boolean hasChocolate() {
        CheckBox schokocheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = schokocheckbox.isChecked();
        return hasChocolate;
    }

    public String getUsername() {
        EditText username_edittext = (EditText) findViewById(R.id.name_edittext);
        return username_edittext.getText().toString();
    }

    public void increaseQuantity(View view) {
        if(numberOfCoffees <100){
        numberOfCoffees += 1;}
        display(numberOfCoffees);
        displayPrice(calculateFinalPrice());
    }

    public void decreaseQuantity(View view) {
        if(numberOfCoffees>0){
        numberOfCoffees -= 1;}
        display(numberOfCoffees);
        displayPrice(calculateFinalPrice());
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public int PricePerCup() {
        int basePrice = 5;
        if (hasCream()) {
            basePrice += 1;
        }
        if (hasChocolate()) {
            basePrice += 2;
        }
        return basePrice;
    }

    public int calculateFinalPrice() {
        return numberOfCoffees * PricePerCup();
    }


    public String OrderSummary(boolean hasCream, boolean hasChocolate, String userName) {
        String message = "Name: " + userName +
                "\nWhipped cream: " + hasCream +
                "\nChocolate: " + hasChocolate +
                "\nQuantity: " + numberOfCoffees +
                "\nTotal: " + calculateFinalPrice() + "$" + "\n" + R.string.thank_you;
        return message;
    }


}

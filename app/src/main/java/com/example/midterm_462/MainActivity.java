package com.example.midterm_462;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable; //Indicates that a method's parameter or return value can be null.
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;  // Helps bind UI components to data sources, reducing boilerplate code for setting up UI.
import com.example.midterm_462.databinding.ActivityMainBinding;  // Automatically generated binding class,
                                                                // which links the layout XML (activity_main.xml) to this activity.
import java.text.DecimalFormat; //Parsing formatted strings back into numbers

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '×';
    private static final char DIVISION = '÷';

    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =   DataBindingUtil.setContentView(this, R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.######");

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });
        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });
        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });
        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });
        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });
        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });
        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });
        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });
        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });
        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });
        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonLeftBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + "(");
            }
        });

        binding.buttonRightBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText(binding.editText.getText() + ")");
            }
        });

        binding.sqrtButton.setOnClickListener(v -> {
            String currentText = binding.editText.getText().toString();
            try {
                double value = Double.parseDouble(currentText);
                double sqrtValue = Math.sqrt(value);
                binding.editText.setText(String.valueOf(sqrtValue));
            } catch (NumberFormatException e) {
                binding.editText.setText("Error");
            }
        });

        // Math Operation Buttons
        binding.buttonAdd.setOnClickListener(v -> handleOperation(ADDITION, " + "));
        binding.buttonSubtract.setOnClickListener(v -> handleOperation(SUBTRACTION, " - "));
        binding.buttonMultiply.setOnClickListener(v -> handleOperation(MULTIPLICATION, " × "));
        binding.buttonDivide.setOnClickListener(v -> handleOperation(DIVISION, " ÷ "));

        binding.buttonEqual.setOnClickListener(v -> {
            computeCalculation();
            binding.infoTextView.setText(binding.infoTextView.getText().toString()
                    + decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
            valueOne = Double.NaN;
            CURRENT_ACTION = '\0';  // Reset action
        });

        binding.buttonClear.setOnClickListener(v -> {
            if (binding.editText.getText().length() > 0) {
                CharSequence currentText = binding.editText.getText();
                binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
                CURRENT_ACTION = '\0';  // Reset action
                binding.editText.setText("");
                binding.infoTextView.setText("");
            }
        });
    }

    private void handleOperation(char action, String symbol) {
        computeCalculation();
        CURRENT_ACTION = action;
        binding.infoTextView.setText(decimalFormat.format(valueOne) + symbol);
        binding.editText.setText(null);
    }

    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            if (binding.editText.getText().length() > 0) {
                try {
                    valueTwo = Double.parseDouble(binding.editText.getText().toString());
                    binding.editText.setText(null);

                    switch (CURRENT_ACTION) {
                        case ADDITION:
                            valueOne += valueTwo;
                            break;
                        case SUBTRACTION:
                            valueOne -= valueTwo;
                            break;
                        case MULTIPLICATION:
                            valueOne *= valueTwo;
                            break;
                        case DIVISION:
                            if (valueTwo != 0) {
                                valueOne /= valueTwo;
                            } else {
                                binding.infoTextView.setText("Error: Division by zero");
                                binding.editText.setText("");
                                return; // Exit to avoid updating with invalid result
                            }
                            break;
                    }
                } catch (NumberFormatException e) {
                    binding.editText.setText("Error");
                }
            }
        } else {
            if (binding.editText.getText().length() > 0) {
                try {
                    valueOne = Double.parseDouble(binding.editText.getText().toString());
                } catch (NumberFormatException e) {
                    binding.editText.setText("Error");
                }
            }
        }
    }
}

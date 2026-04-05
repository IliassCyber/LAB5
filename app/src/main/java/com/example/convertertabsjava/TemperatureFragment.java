package com.example.convertertabsjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TemperatureFragment extends Fragment {

    private RadioGroup selectionGroup;
    private RadioButton optionCtoF;
    private EditText inputField;
    private Button computeBtn;
    private TextView displayResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_temperature, container, false);

        selectionGroup = root.findViewById(R.id.temp_radio_group);
        optionCtoF = root.findViewById(R.id.radio_celsius_to_fahrenheit);
        inputField = root.findViewById(R.id.input_temperature_value);
        computeBtn = root.findViewById(R.id.button_compute_temp);
        displayResult = root.findViewById(R.id.text_temperature_display);

        computeBtn.setOnClickListener(view -> performCalculation());

        return root;
    }

    private void performCalculation() {
        String valueStr = inputField.getText().toString().trim();
        
        if (TextUtils.isEmpty(valueStr)) {
            Toast.makeText(getContext(), "Entrez une valeur numérique", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double inputVal = Double.parseDouble(valueStr);
            double calculated;

            if (optionCtoF.isChecked()) {
                calculated = (inputVal * 1.8) + 32;
            } else {
                calculated = (inputVal - 32) / 1.8;
            }

            displayResult.setText(String.format(Locale.getDefault(), "Valeur calculée : %.2f", calculated));
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Format invalide", Toast.LENGTH_SHORT).show();
        }
    }
}

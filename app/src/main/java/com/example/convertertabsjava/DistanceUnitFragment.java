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

public class DistanceUnitFragment extends Fragment {

    private RadioGroup unitChoiceGroup;
    private RadioButton radioKmToMi;
    private EditText distanceInput;
    private Button convertButton;
    private TextView resultDisplay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_distance_unit, container, false);

        unitChoiceGroup = layoutView.findViewById(R.id.dist_unit_radio_group);
        radioKmToMi = layoutView.findViewById(R.id.radio_km_to_miles);
        distanceInput = layoutView.findViewById(R.id.input_distance_value);
        convertButton = layoutView.findViewById(R.id.button_compute_dist);
        resultDisplay = layoutView.findViewById(R.id.text_distance_result_view);

        convertButton.setOnClickListener(v -> processConversion());

        return layoutView;
    }

    private void processConversion() {
        String inputStr = distanceInput.getText().toString().trim();
        
        if (TextUtils.isEmpty(inputStr)) {
            Toast.makeText(getContext(), "Veuillez entrer une distance", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double distance = Double.parseDouble(inputStr);
            double convertedValue;

            if (radioKmToMi.isChecked()) {
                convertedValue = distance * 0.621371;
            } else {
                convertedValue = distance / 0.621371;
            }

            resultDisplay.setText(String.format(Locale.getDefault(), "Résultat : %.2f", convertedValue));
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Saisie invalide", Toast.LENGTH_SHORT).show();
        }
    }
}

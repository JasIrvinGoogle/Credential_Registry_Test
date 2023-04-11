package com.example.credential_registry_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.credential_registry_test.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    String[] item = {"mDoc", "something1", "something2"};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;


    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        View txtview = inflater.inflate(R.layout.fragment_second, container, false);

        binding = FragmentSecondBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autoCompleteTextView = getView().findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(getActivity() , R.layout.list_item,item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        //Back button
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
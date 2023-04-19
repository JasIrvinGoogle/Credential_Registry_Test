package com.example.credential_registry_test;

import static android.widget.AdapterView.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.credential_registry_test.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    String[] item = {"mDoc", "password", "passkey"};
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
    private ListView languageLV;
    private Button addBtn;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // on below line we are initializing our variables.
        languageLV = view.findViewById(R.id.idLVLanguages);
        addBtn = view.findViewById(R.id.idBtnAdd);
        itemEdt = view.findViewById(R.id.listAttributes);
        lngList = new ArrayList<>();
        // on below line we are adding items to our list
        // lngList.add("C++");
        // lngList.add("Python");

        // on the below line we are initializing the adapter for our list view.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lngList);

        // on below line we are setting adapter for our list view.
        languageLV.setAdapter(adapter);

//        languageLV.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,int i, int position, long id) {
////                Toast.makeText("Removed: " + lngList.get(i));
//                removeItem();
//                return false;
//            }
//        });



        languageLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Toast.makeText(getActivity(), "Removed: " +lngList.get(position), Toast.LENGTH_SHORT).show();
                lngList.remove(position);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            }

        });




        // on below line we are adding click listener for our button.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String item = itemEdt.getText().toString();

                // on below line we are checking if item is not empty
                if (!item.isEmpty()) {

                    // on below line we are adding item to our list.
                    lngList.add(item);
                    itemEdt.getText().clear();

                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    adapter.notifyDataSetChanged();
                }

            }
        });









        autoCompleteTextView = getView().findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(getActivity() , R.layout.list_item,item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                Toast.makeText(getActivity(), "Type Selected: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        //Back button

        Button buttonSecond = view.findViewById(R.id.button_second);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }


    public void removeItem(int remove){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lngList);

        lngList.remove(remove);

        // on below line we are notifying adapter
        // that data in list is updated to
        // update our list view.
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.example.mn.simpleandroidslideappwithfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;






public class ViewPagerFragment extends Fragment {

    private TextView textView;
    private ImageView imageView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://praca-inzynierska-omm-omm-site.appspot.com/zdjecie1.jpg");


        Log.d("element", "Stworzono" );
        View view = inflater.inflate(R.layout.content_main, null);
        textView =  view.findViewById(R.id.contentTextView);
        String title = getArguments().getString("title");
        textView.setText(title);
        imageView = view.findViewById(R.id.contentImageView);
        GlideApp.with(this /* context */)
                .load(storageReference)
                .into(imageView);

        return view;
    }

}

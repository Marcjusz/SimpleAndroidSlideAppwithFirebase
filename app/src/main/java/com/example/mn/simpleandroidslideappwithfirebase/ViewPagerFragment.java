package com.example.mn.simpleandroidslideappwithfirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class ViewPagerFragment extends Fragment {

    private TextView textView;
    private ImageView imageView;
    private StorageReference storageReference;
    private File fileLocal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        /* didint work GlideApp
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://praca-inzynierska-omm-omm-site.appspot.com/zdjecie1.jpg");
         imageView = view.findViewById(R.id.contentImageView);
        GlideApp.with(this).load(storageReference).into(imageView);
        */


        View view = inflater.inflate(R.layout.content_main, null);
        return setContentofFragment(view);
    }

    public View setContentofFragment( View view){
        storageReference = FirebaseStorage.getInstance().getReference();
        String id = getArguments().getString("id");
        storageReference = storageReference.child("Base").child("Slider").child(id+".jpg");
        imageView = view.findViewById(R.id.contentImageView);

        try {
            fileLocal = File.createTempFile("image", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storageReference.getFile(fileLocal)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        Bitmap bitmap = BitmapFactory.decodeFile(fileLocal.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });



        textView =  view.findViewById(R.id.contentTextView);
        String title = getArguments().getString("title");
        textView.setText(title);
        textView.setTextSize(30);

        //StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/praca-inzynierska-omm-omm-site.appspot.com/o/zdjecie1.jpg?alt=media&token=4d47271b-7c10-4084-92e0-0cd85a5f1b40");
        //GlideApp.with(this).load(storageReference).into(imageView);
        //imageView.setImageResource(R.drawable.ic_launcher_background);


        return view;
    }

}

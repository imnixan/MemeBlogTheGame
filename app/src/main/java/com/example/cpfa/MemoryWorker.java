package com.example.cpfa;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.example.cpfa.UpdatesChecker.mems;
public class MemoryWorker {

    Context context;

    MemoryWorker(Context context) {
        this.context = context;
    }


    public void getMems() {
        try {
            FileInputStream fis = context.openFileInput("memeses.txt");
            ObjectInputStream is = new ObjectInputStream(fis);
            mems = (ArrayList<String>) is.readObject();
            is.close();
            fis.close();
        }catch (Exception e){
            System.out.println("Error in getMems " + e);
        }
    }


    public void saveMems(ArrayList<String> memeses){
                try{
                    FileOutputStream fos = context.openFileOutput("memeses.txt", Context.MODE_PRIVATE);
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    os.writeObject(memeses);
                    os.close();
                    fos.close();
                }catch (Exception e){
                    System.out.println("Error in saveMems " + e);
                }

    }
}
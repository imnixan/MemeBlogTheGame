package com.example.cpfa;


import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

public class UpdatesChecker extends Thread {
    UpdateCallback updateCallback;
    DatabaseSQL db;
    static public ArrayList<String> mems = new ArrayList<>();
    Context context;
    MemoryWorker memoryWorker;

    UpdatesChecker(UpdateCallback updateCallback, Context context) {
        this.updateCallback = updateCallback;
        this.context = context;
        start();
    }

    @Override
    public void run() {

            memoryWorker = new MemoryWorker(context);
            memoryWorker.getMems();
            db = new DatabaseSQL();
            String rawansw = db.getString("count");
            System.out.println("Raw - "  +  rawansw);
            int answer = Integer.parseInt(rawansw);

            if (answer > mems.size()){
                updateCallback.updateText();
                System.out.println(mems.size());
                gettingUpdates(answer);
            }else {
            updateCallback.onSucces();
            System.out.println("No need updates - " + mems);

            }

    }
    public void gettingUpdates(int answer){
        for (int i = mems.size(); i <=answer; i++){
            mems.add(db.getString(Integer.toString(i)));
        }
        memoryWorker.saveMems(mems);
        updateCallback.onSucces();
        
    }
}

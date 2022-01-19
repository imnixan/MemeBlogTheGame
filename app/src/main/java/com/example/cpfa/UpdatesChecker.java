package com.example.cpfa;


import java.util.ArrayList;

public class UpdatesChecker extends Thread {
    UpdateCallback updateCallback;
    DatabaseSQL db;
    static public ArrayList<String> mems = new ArrayList<>();

    UpdatesChecker(UpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
        start();
    }

    @Override
    public void run() {

            db = new DatabaseSQL();
            String rawansw = db.getString("1");
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
        for (int i = mems.size()+1; i <=answer+1; i++){
            mems.add(db.getString(Integer.toString(i)));
        }
        updateCallback.onSucces();
        
    }
}

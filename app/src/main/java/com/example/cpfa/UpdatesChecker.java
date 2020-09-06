package com.example.cpfa;


public class UpdatesChecker extends Thread {
    UpdateCallback updateCallback;


    UpdatesChecker(UpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
        start();
    }

    @Override
    public void run() {
        try {
            this.sleep(2000);
            System.out.println("go to sql");
            DatabaseSQL db = new DatabaseSQL();
            db.getString("3");
            updateCallback.updateText();
            this.sleep(15000);
            updateCallback.onSucces();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

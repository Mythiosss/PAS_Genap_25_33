package com.example.pas_25_33;

public class ModelPlayer {
    private String strPlayer;
    private String dateBorn;
    private String strBadge;
    public ModelPlayer() {}

    public ModelPlayer(String strPlayer, String dateBorn, String strBadge) {
        this.strPlayer = strPlayer;
        this.dateBorn = dateBorn;
        this.strBadge = strBadge;
    }

    public String getStrPlayer() {
        return strPlayer;
    }

    public String getDateBorn(){
        return dateBorn;
    }

    public String getStrStadium() {
        return strStadium;
    }
}

package com.example.pas_25_33;

public class ModelPlayer {
    private String strPlayer;
    private String dateBorn;
    private String strThumb;
    private String strNationality;
    public ModelPlayer() {}

    public ModelPlayer(String strPlayer, String dateBorn, String strBadge, String strNationality) {
        this.strPlayer = strPlayer;
        this.dateBorn = dateBorn;
        this.strThumb = strBadge;
        this.strNationality = strNationality;
    }

    public String getStrPlayer() {
        return strPlayer;
    }
    public String getStrNationality() {
        return strNationality;
    }

    public String getDateBorn(){
        return dateBorn;
    }

    public String getStrThumb() {
        return strThumb;
    }
}

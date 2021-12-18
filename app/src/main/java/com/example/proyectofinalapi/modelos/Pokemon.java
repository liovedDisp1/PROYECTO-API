package com.example.proyectofinalapi.modelos;

public class Pokemon {
    private String name;
    private String url;
    private String ability;
    private int number;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getNumber() {
        String[] urlpartes = url.split("/");
        return Integer.parseInt(urlpartes[urlpartes.length-1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package org.example.turistguide2.model;

public enum Cities {
    Copenhagen(1), Zealand(2), Roskilde(3), Jutland(4), Fyn(5), Odense(6), Aarhus(7), Bornholm(8);

    //Bruges til at tildele enummet et ID, til at hente fra Cities tabellen i DB
    private final int id;

    Cities(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //Bruges til hente bynavn ud fra et ID fra DB
    public static Cities fromId(int id) {
        for (Cities c : values()) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }
}


package org.example.turistguide2.model;

public enum Cities {
    Copenhagen(1), Zealand(2), Roskilde(3), Jutland(4), Fyn(5), Odense(6), Aarhus(7), Bornholm(8);

    private final int id;

    Cities(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Cities fromId(int id) {
        for (Cities c : values()) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }
}


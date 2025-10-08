package org.example.turistguide2.model;

public enum Tags {
    Park(1), Stadion(2), School(3), Library(4), Fun(5), Educational(6), Museum(7), Free(8);

    private final int id;

    Tags(int id) {
        this.id = id;
    }

    public int getId () {
        return id;
    }

    public static Tags fromId (int id) {
        for (Tags t : values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }
}


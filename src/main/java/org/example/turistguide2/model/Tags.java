package org.example.turistguide2.model;

//Enum der repræsenterer de forskellige typer tags, samt tilhørende ID.
public enum Tags {
    Park(1), Stadion(2), School(3), Library(4), Fun(5), Educational(6), Museum(7), Free(8);

    //Samme som i Cities klassen
    private final int id;

    Tags(int id) {
        this.id = id;
    }

    public int getId () {
        return id;
    }

    //Samme som i Cities klassen
    public static Tags fromId (int id) {
        for (Tags t : values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }
}


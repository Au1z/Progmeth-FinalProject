package item.area;

import player.Player;

public class Area {
    private Player owner;
    private int level;

    public Area() {
        setLevel(0);
        setOwner(new Player());
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(0, level);
    }
}

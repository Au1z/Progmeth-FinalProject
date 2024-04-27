package item.area;

import player.Player;

public class Area {
    private Player owner;
    private int level;
    private boolean isOwned;

    public Area() {
        setLevel(0);
        setOwner(new Player());
        isOwned = false;
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

    public boolean isOwneded() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public boolean canBuy() {
        return !isOwned || owner == null;
    }
}

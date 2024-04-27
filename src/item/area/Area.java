package item.area;

import player.Player;

public class Area {
    private Player owner;
    private int level;
    private boolean owned;

    public Area() {
        setLevel(0);
        setOwner(new Player());
        owned = false;
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

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean canBuy(Player player) {
        if (!owned || owner == null ) {
            return true;
        } else {
            return false;
        }
    }
}

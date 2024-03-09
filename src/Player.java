public class Player {
    private int hp;
    private int position;

    public Player() {
        setHp(Config.PlayerStartHp);
        setPosition(Config.PlayerStartPosition);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = Math.max(0, Math.min(Config.NumberOfArea, position));
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }
}

package item;

import game.Config;

public class Player {
    private String name;
    private int hp;
    private int position;
    private boolean isWin;

    public Player() {
        setName("");
        setHp(Config.PlayerStartHp);
        setPosition(Config.PlayerStartPosition);
        this.isWin = false;
    }

    public void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }

    public Player(String name) {
        setName(name);
        setHp(Config.PlayerStartHp);
        setPosition(Config.PlayerStartPosition);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = Math.max(0, position % Config.NumberOfArea);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public boolean getIsWin(){
        return this.isWin;
    }

    @Override
    public String toString() {
        return name;
    }
}

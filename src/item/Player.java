package item;

import game.Config;
import javafx.scene.image.Image;

public class Player {
    private String name;
    private int hp;
    private int position;
    private boolean isWin;
    private Image image;
    private Image miniImage;

    public Player() {
        setName("");
        setHp(Config.PlayerStartHp);
        setPosition(Config.PlayerStartPosition);
    }

    public Player(String name, Image image, Image miniImage) {
        setName(name);
        setHp(Config.PlayerStartHp);
        setPosition(Config.PlayerStartPosition);
        setImage(image);
        setMiniImage(miniImage);
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

    public void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getMiniImage() {
        return miniImage;
    }

    public void setMiniImage(Image miniImage) {
        this.miniImage = miniImage;
    }

    @Override
    public String toString() {
        return name;
    }
}

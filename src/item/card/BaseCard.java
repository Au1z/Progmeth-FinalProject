package item.card;

import javafx.scene.image.Image;

public abstract class BaseCard implements Activatable{
    private String name;
    private boolean isUsed;
    private Image image;

    public BaseCard(String name, Image image) {
        setName(name);
        setUsed(false);
        setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String effect() {
        return "effect it will be difference it every type card";
    }
}


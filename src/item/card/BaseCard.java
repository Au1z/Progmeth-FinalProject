package item.card;

public abstract class BaseCard implements Activatable{
    private String name;
    private boolean isUsed;

    public BaseCard(String name) {
        setName(name);
        setUsed(false);
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

    public String effect() {
        return "effect it will be difference it every type card";
    }
}


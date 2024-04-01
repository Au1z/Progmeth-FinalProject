package card;

public abstract class BaseCard {
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
}

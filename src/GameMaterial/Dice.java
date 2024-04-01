package GameMaterial;

import java.util.Random;

public class Dice {
    private int faceValue;

    public Dice() {
        setFaceValue(0);
    }

    public void randomFaceValue() {
        setFaceValue(new Random().nextInt(6) + 1);
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = Math.max(1, Math.min(6, faceValue));
    }
}

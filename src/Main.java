public class Main {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        System.out.println("Game Start");
        Dice dice = new Dice();
        while (player1.getHp() != 0 && player2.getHp() != 0 && dice.getFaceValue() != 6) {
            dice.randomFaceValue();
            System.out.println(dice.getFaceValue());
        }
        System.out.println("Game End");
    }
}
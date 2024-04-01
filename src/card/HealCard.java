package card;
import GameMaterial.Player;

public class HealCard extends BaseCard{
    private Player player;
    public HealCard() {
        super("HEAL");
    }
    public void activate(Player player) {
        player.setHp(player.getHp() + 2);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

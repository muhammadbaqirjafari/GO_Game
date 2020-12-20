package go_game;

public class BeadSpecification {
    private PlayerID playerID;
    private final BeadLOC loc;

    public BeadSpecification(PlayerID playerID, BeadLOC loc)
    {
        this.loc = loc;
        this.playerID = playerID;
    }

    public BeadLOC getLoc(){
        return loc;
    }

    public PlayerID getPlayerID(){
        return playerID;
    }
}

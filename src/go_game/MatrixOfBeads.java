package go_game;

public class MatrixOfBeads {
    private final int length;
    private final int width;
    private Bead[][] beads;

    // Constructor
    public MatrixOfBeads(int length, int width){
        this.length = length;
        this.width = width;
        this.beads = new Bead[length][width];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++) {
                this.beads[i][j] = new Bead(new BeadSpecification(new PlayerID(0), new BeadLOC(i, j)));
            }
        }
    }

    // Getter
    public Bead getBead(BeadLOC loc){
        return beads[loc.getX()][loc.getY()];
    }

    // Setters
    public void setBead(BeadSpecification beadSpec) {
        beads[beadSpec.getLoc().getX()][beadSpec.getLoc().getY()] = new Bead(beadSpec);
    }
    
    // Public Method
    public void display() {
        PlayerID player1ID = new PlayerID(1);
        PlayerID player2ID = new PlayerID(2);
        
        // Displaying Scale
        for(int i = 0; i <= beads.length; ++i) {
            System.out.print(i + "\t");
        }
        System.out.println(); // New Line
        
        for(int i = 0; i < beads[0].length; ++i) {
            System.out.print(i + 1 + "\t");
            for(int j = 0; j < beads.length; ++j) {
                PlayerID playerID = beads[i][j].getBeadSpecification().getPlayerID();
                
                if(playerID.getPlayerID() == 1) {
                    System.out.print("1\t");
                } else if(playerID.getPlayerID() == 2) {
                    System.out.print("2\t");                    
                } else {
                    System.out.print("0\t");                    
                }
            }
            System.out.println();   // New Line
        }
        System.out.println();   // New Line
    }
}

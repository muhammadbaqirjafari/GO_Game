package go_game;

public class MatrixOfBeads {
    private final int length;
    private final int width;
    private Bead[][] beads;

    public MatrixOfBeads(int length, int width){
        this.length = length;
        this.width = width;
        this.beads = new Bead[length][width];
        for(int i = 0; i<length; i++){
            for(int j = 0; j<width; j++) {
                this.beads[i][j] = new Bead(new BeadSpecification(new PlayerID(0), new BeadLOC(i, j)));
            }
        }
    }
    public void updateTable(int[][] dp) {
        for(int i = 0; i<length; i++){
            for(int j = 0; j<width; j++) {
                this.beads[i][j] = new Bead(new BeadSpecification(new PlayerID(dp[i][j]), new BeadLOC(i, j)));
            }
        }
    }
    public Bead getBead(BeadLOC loc){
        return beads[loc.getX()][loc.getY()];
    }

    public void setBead(BeadSpecification beadSpec) {
        beads[beadSpec.getLoc().getX()][beadSpec.getLoc().getY()] = new Bead(beadSpec);
    }
}

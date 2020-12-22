package go_game;

public class Board {
    private BoardSpecification spec;
    private MatrixOfBeads matrixOfBeads;

    public Board(BoardSpecification spec) {
        this.spec = spec;
        this.matrixOfBeads = new MatrixOfBeads(spec.getWidth(), spec.getHeight());
    }

    // Getter
    public BoardSpecification getBoardSpecification() {
        return spec;
    }
    
    
    public void placeBead(BeadSpecification spec) {
        matrixOfBeads.setBead(spec);
    }

    public BeadSpecification getBeadSpecification(BeadLOC loc) {
        return matrixOfBeads.getBead(loc).getBeadSpecification();
    }

    public void removeBead(BeadLOC loc) {
        matrixOfBeads.setBead(new BeadSpecification(new PlayerID(0), loc));
    }
    public void updateTable(int[][] dp) {
        matrixOfBeads.updateTable(dp);
    }
    public void display() {
        for (int j = 0; j<spec.getHeight(); j++ ) {
                System.out.print("----");
        }
        System.out.print("\n");    
        for(int i = 0; i<spec.getWidth(); i++) {
            for(int j = 0; j<spec.getHeight(); j++) {
                int b = matrixOfBeads.getBead(new BeadLOC(i, j)).getBeadSpecification().getPlayerID().getPlayerID();
                if(b == 0) 
                    System.out.print("|   ");
                else if(b == 1)
                    System.out.print("| 1 ");
                else
                    System.out.print("| 2 ");
            }
            System.out.print("|\n");
            for (int j = 0; j<spec.getHeight(); j++ ) {
                System.out.print("----");
            }
            System.out.print("\n");
        }
         System.out.print("\n");
    }
}

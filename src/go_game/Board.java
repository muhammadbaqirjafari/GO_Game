package go_game;

public class Board {
    private BoardSpecification spec;
    private MatrixOfBeads matrixOfBeads;

    public Board(BoardSpecification spec) {
        this.spec = spec;
        this.matrixOfBeads = new MatrixOfBeads(spec.getHeight(), spec.getWidth());
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
}

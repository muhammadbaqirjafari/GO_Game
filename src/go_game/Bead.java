package go_game;

public class Bead {
    private BeadSpecification spec;

    public Bead(BeadSpecification spec)
    {
        this.spec = spec;
    }

    public BeadSpecification getBeadSpecification() {
        return spec;
    }
}

package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double DEFAULT_ENERGY = 60;
    private static final double DEFAULT_DECREASE_ENERGY = 7;
    public NaturalExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void search() {
        super.setEnergy( Math.max(0, getEnergy() - DEFAULT_DECREASE_ENERGY));
    }
}

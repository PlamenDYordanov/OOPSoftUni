package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static  final  double DEFAULT_ENERGY = 60;
    public NaturalExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void search() {
        setEnergy(Math.max(0, getEnergy() - 7));
    }
}

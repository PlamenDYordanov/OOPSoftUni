package football.entities.field;

import football.entities.player.BasePlayer;

public class ArtificialTurf extends BaseField {
    private static final int DEFAULT_CAPACITY = 150;

    public ArtificialTurf(String name) {
        super(name, DEFAULT_CAPACITY);
    }
}

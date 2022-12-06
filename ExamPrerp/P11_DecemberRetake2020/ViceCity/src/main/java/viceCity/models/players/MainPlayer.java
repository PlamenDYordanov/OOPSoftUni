package viceCity.models.players;

public class MainPlayer extends BasePlayer{
    private static final  int DEFAULT_LIFE_POINTS = 100;
    private static final String DEFAULT_NAME = "Tommy Vercetti";
    public MainPlayer() {
        super(DEFAULT_NAME, DEFAULT_LIFE_POINTS);
    }

}

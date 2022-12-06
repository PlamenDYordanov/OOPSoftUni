package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 10;
    private static int DEFAULT_TOTAL_BULLETS = 100;
    private static final int DEFAULT_SHOOT_BULLET = 1;

    public Pistol(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }


    @Override
    public int fire() {
        setBulletsPerBarrel(getBulletsPerBarrel() - 1);
        if (getBulletsPerBarrel() == 0 && getTotalBullets() >= 10) {
            reload();
        }
        return DEFAULT_SHOOT_BULLET;
    }

    private void reload() {
        setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
        setTotalBullets(getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
    }
}
package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 50;
    private static int DEFAULT_TOTAL_BULLETS = 500;
    private static final int DEFAULT_SHOOT_BULLET = 5;

    public Rifle(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }


    @Override
    public int fire() {
        setBulletsPerBarrel(getBulletsPerBarrel() - 5);
        if (getBulletsPerBarrel() == 0 && getTotalBullets() >= 50) {
            reload();
        }
        return DEFAULT_SHOOT_BULLET;
    }

    private void reload() {
        setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
        setTotalBullets(getTotalBullets() - DEFAULT_BULLETS_PER_BARREL);
    }
}


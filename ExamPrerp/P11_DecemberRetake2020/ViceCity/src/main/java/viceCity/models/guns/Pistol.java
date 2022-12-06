package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 10;
    private static final int DEFAULT_TOTAL_BULLETS = 100;
    private static final int DEFAULT_SHOOT_BULLET = 1;

    public Pistol(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }



    @Override
    public int fire() {
        if (!canFire()) {
            setTotalBullets(getTotalBullets() -(DEFAULT_BULLETS_PER_BARREL- getBulletsPerBarrel()));
            setBulletsPerBarrel(DEFAULT_BULLETS_PER_BARREL);
        }
        setBulletsPerBarrel(getTotalBullets() - DEFAULT_SHOOT_BULLET);
        setTotalBullets(getTotalBullets() - 1);

        return DEFAULT_SHOOT_BULLET;
    }
}

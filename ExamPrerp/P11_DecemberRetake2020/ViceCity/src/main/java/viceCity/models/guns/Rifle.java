package viceCity.models.guns;

public class Rifle extends BaseGun{
    private static final int DEFAULT_BULLETS_PER_BARREL = 50;
    private static final int DEFAULT_TOTAL_BULLETS = 500;
    private static final int DEFAULT_SHOOT_BULLET = 5;
    public Rifle(String name) {
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

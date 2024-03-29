package viceCity.models.guns;

public class Rifle extends BaseGun {
    private  static int totalBullets = 500;
    private static int  bulletPerBarrel = 50;
    private static final int BULLETS_PER_BARREL = 50;
    private static final int BULLETS_PER_SHOOT = 5;



    public Rifle(String name) {
        super(name, bulletPerBarrel, totalBullets);
    }


    @Override
    public boolean canFire() {
        return getTotalBullets() >= BULLETS_PER_SHOOT;
    }

    @Override
    public int fire() {
        setBulletsPerBarrel(getBulletsPerBarrel() - 5);
        if (getBulletsPerBarrel() == 0 && getTotalBullets() >= 50) {
            reload();
        }
        return BULLETS_PER_SHOOT;
    }

    private void reload() {
        setBulletsPerBarrel(BULLETS_PER_BARREL);
        setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
    }
}


package viceCity.models.guns;

public class Pistol extends BaseGun {

    private  static int totalBullets = 100;
    private static int  bulletPerBarrel = 10;
    private static final int BULLETS_PER_BARREL = 10;
    private static final int BULLETS_PER_SHOOT = 1;


    public Pistol(String name) {
        super(name, bulletPerBarrel, totalBullets);
    }


    @Override
    public boolean canFire() {
        return getTotalBullets() >= BULLETS_PER_SHOOT;
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0) {
            reload();
        }
        setBulletsPerBarrel(getBulletsPerBarrel() - BULLETS_PER_SHOOT);
        return BULLETS_PER_SHOOT;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - getBulletsPerBarrel());
        setBulletsPerBarrel(BULLETS_PER_BARREL);
    }
}

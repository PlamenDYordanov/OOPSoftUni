package magicGame.models.magics;

public class BlackMagic extends MagicImpl{
    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() - 10 >= 0){
            setBulletsCount(Math.max(0, getBulletsCount() - 10));
        }else {
            setBulletsCount(0);
            return 0;
        }
        return 10;
    }
}

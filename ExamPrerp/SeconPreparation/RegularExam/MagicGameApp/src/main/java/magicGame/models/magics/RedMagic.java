package magicGame.models.magics;

public class RedMagic extends MagicImpl{
    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() - 1 >= 0){
            setBulletsCount(Math.max(0, getBulletsCount() - 1));
        }else {
            setBulletsCount(0);
            return 0;
        }
        return 1;
    }
}

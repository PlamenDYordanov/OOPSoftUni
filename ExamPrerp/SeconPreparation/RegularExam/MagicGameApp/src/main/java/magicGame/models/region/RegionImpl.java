package magicGame.models.region;

import magicGame.models.magicians.Magician;

import java.util.ArrayList;
import java.util.Collection;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        Collection<Magician> wizards = new ArrayList<>();
        Collection<Magician> blackWidow = new ArrayList<>();
        for (Magician magician : magicians) {
            if (magician.getClass().getSimpleName().equals("BlackWidow")) {
                blackWidow.add(magician);
            } else if (magician.getClass().getSimpleName().equals("Wizard")) {
                blackWidow.add(magician);
            }
        }
        for (Magician wizard : wizards) {
            for (Magician currWidow : blackWidow) {
                if (wizard.getMagic().getBulletsCount() > 0) {
                    currWidow.takeDamage(wizard.getMagic().fire());
                }

                if (currWidow.getMagic().getBulletsCount() > 0) {
                    wizard.takeDamage(currWidow.getMagic().fire());
                }
                break;
            }
        }
        return null;
    }
}

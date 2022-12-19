package magicGame.models.region;

import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RegionImpl implements Region {
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = new ArrayList<>();
        List<Magician> blackWidow = new ArrayList<>();
        for (Magician magician : magicians) {
            if (magician.getClass().getSimpleName().equals("BlackWidow")) {
                blackWidow.add(magician);
            }
            if (magician.getClass().getSimpleName().equals("Wizard")) {
                wizards.add(magician);
            }
        }
        int indexWizard = 0;
        int indexWidow = 0;
        StringBuilder output = new StringBuilder();
        while (!isAllDead(wizards) && !isAllDead(blackWidow)) {
            int sizeOfListWizard = wizards.size();
            int sizeOfWidows = blackWidow.size();

            if (indexWizard == wizards.size()) {
                indexWizard = 0;
            }
            if (indexWidow == blackWidow.size()) {
                indexWidow = 0;
            }
            Magician currentWizard = wizards.get(indexWizard);
            for (Magician currWidow : blackWidow) {
                if (currWidow.isAlive()) {
                    if (currentWizard.getMagic().getBulletsCount() > 0) {
                        int fire = currentWizard.getMagic().fire();
                        currWidow.takeDamage(fire);
                    }
                }
            }
            if (isAllDead(blackWidow)){
                break;
            }

            Magician currentWidow = blackWidow.get(indexWidow);
            for (Magician wizard : wizards) {
                if (wizard.isAlive()) {
                    if (currentWidow.getMagic().getBulletsCount() > 0) {
                        int fire = currentWidow.getMagic().fire();
                                wizard.takeDamage(fire);
                    }
                }
            }
            if (isAllDead(wizards)){
                break;
            }
            indexWizard++;
            indexWidow++;



        }
        if (isAllDead(wizards)) {
            output.append("Black widows win!");
        }else {
            output.append("Wizards win!");
        }


        return output.toString();
    }

    private boolean isAllDead(List<Magician> wizards) {
        for (Magician wizard : wizards) {
            if (wizard.isAlive()) {
                return false;
            }
        }
        return true;
    }
}







package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.MagicRepositoryImpl;
import magicGame.repositories.MagicianRepositoryImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller {
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        switch (type) {
            case "RedMagic":
                magic = new RedMagic(name, bulletsCount);
                break;
            case "BlackMagic":
                magic = new BlackMagic(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }
        this.magics.addMagic(magic);
        return String.format(SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magicByName = magics.findByName(magicName);
        if (magicByName == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }
        Magician magician;
        switch (type) {
            case "Wizard":
                magician = new Wizard(username, health, protection, magicByName);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magicByName);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }
        this.magicians.addMagician(magician);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
        Region region1 = new RegionImpl();
        return region1.start(magicians.getData());
    }

    @Override
    public String report() {
        List<Magician> ordered =
                magicians.getData().stream()
                        .sorted(Comparator.comparingInt(Magician::getHealth)
                                .thenComparing(Magician::getUsername)
                                .thenComparing(left -> left.getClass().getSimpleName())).collect(Collectors.toList());
        StringBuilder output = new StringBuilder();
        for (Magician magician : ordered) {
            output.append(magician.toString());
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}

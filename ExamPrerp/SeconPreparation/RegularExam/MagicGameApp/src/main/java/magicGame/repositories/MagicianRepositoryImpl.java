package magicGame.repositories;

import magicGame.models.magicians.Magician;
import magicGame.repositories.interfaces.MagicianRepository;

import java.util.ArrayList;
import java.util.Collection;

import static magicGame.common.ExceptionMessages.INVALID_MAGICIAN_REPOSITORY;

public class MagicianRepositoryImpl implements MagicianRepository<Magician> {
    private Collection<Magician> data;

    public MagicianRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection<Magician> getData() {
        return this.data;
    }

    @Override
    public void addMagician(Magician model) {
        if (model == null) {
            throw new NullPointerException(INVALID_MAGICIAN_REPOSITORY);
        }
        this.data.add(model);
    }

    @Override
    public boolean removeMagician(Magician model) {
        return this.data.remove(model) ;
    }

    @Override
    public Magician findByUsername(String name) {
        return this.data.stream().filter(name1 -> name1.getUsername().equals(name)).findFirst().orElse(null);
    }
}

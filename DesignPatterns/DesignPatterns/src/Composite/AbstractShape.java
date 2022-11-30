package Composite;

public abstract class AbstractShape implements Shape{

    private boolean selected = false;
    @Override
    public void select() {
        this.selected = true;
    }

    @Override
    public void unselect() {
        this.selected = false;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }
}

package Abstraction.Exercise.P04_TrafficLights;

public class Light {
    private Color color;

    public Light(Color color) {
        this.color = color;
    }


    public void changeLight() {
        if (getColor() == Color.RED) {
            setColor(Color.GREEN);
        } else if (getColor() == Color.GREEN) {
            setColor(Color.YELLOW);
        } else if (getColor() == Color.YELLOW) {
            setColor(Color.RED);
        }

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

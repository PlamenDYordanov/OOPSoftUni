package onlineShop.models.products.computers;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer  extends BaseProduct implements Computer{
    private List<Component> components;
    private List<Peripheral> peripherals;
    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }
    @Override
    public double getOverallPerformance() {
        if (getComponents().isEmpty()) {
            return super.getOverallPerformance();
        }
        double averagePerformance = getComponents().stream().mapToDouble(Product::getOverallPerformance).average().getAsDouble();
        return averagePerformance + super.getOverallPerformance();
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }


    @Override
    public double getPrice() {
        return super.getPrice() + components
                .stream()
                .mapToDouble(Component::getPrice)
                .sum() +
                peripherals
                        .stream()
                        .mapToDouble(Peripheral::getPrice)
                        .sum();
    }

    @Override
    public void addComponent(Component component) {
        for (Component component1 : components) {
            if (component1.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,component.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),getId()));
            }
        }
        components.add(component);

    }

    @Override
    public Component removeComponent(String componentType) {
        if (components.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,componentType,
                    this.getClass().getSimpleName(),getId()));
        }
        Component component = components.stream()
                .filter(component1 -> component1.getClass().getSimpleName().equals(componentType))
                .findFirst()
                .orElse(null);
        if (component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,componentType,
                    this.getClass().getSimpleName(),getId()));
        }
        this.components.remove(component);

        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral peripheral1 : this.peripherals) {
            if (peripheral1.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,peripheral.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),getId()));
            }
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (this.peripherals.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,peripheralType,
                    this.getClass().getSimpleName(),getId()));
        }
        Peripheral peripheral = peripherals.stream()
                .filter(peripheral1 -> peripheral1.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);
        if (peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,peripheralType,
                    this.getClass().getSimpleName(),getId()));
        }
        this.peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());

        builder.append(System.lineSeparator());

        builder.append(String.format(" " + OutputMessages.COMPUTER_COMPONENTS_TO_STRING, components.size()));

        builder.append(System.lineSeparator());

        for (Component component : components) {
            builder.append("  ")
                    .append(component.toString())
                    .append(System.lineSeparator());
        }

        builder.append(String.format(" " + OutputMessages.COMPUTER_PERIPHERALS_TO_STRING,
                peripherals.size(), peripherals.stream()
                        .mapToDouble(Peripheral::getOverallPerformance).average().orElse(0)));

        builder.append(System.lineSeparator());

        for (Peripheral peripheral : peripherals) {
            builder.append("  ")
                    .append(peripheral.toString())
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}

package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
    }

    @Override
    public double getPrice() {
        double priceComponent = components.stream().mapToDouble(Product::getPrice).sum();
        double pricePeripheral = peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        double totalSum = pricePeripheral + priceComponent;
        return totalSum;
    }
    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public double getOverallPerformance() {
        double averagePerformance = 0;
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            double sum = components.stream().mapToDouble(Product::getOverallPerformance).sum();
            averagePerformance = sum / components.size();
        }
        return getOverallPerformance() + averagePerformance;
    }



    @Override
    public void addComponent(Component component) {

        for (Component component1 : components) {
            if (component1.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
            }
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        boolean isExist = false;
        int index = 0;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                isExist = true;
                break;
            }
            index++;
        }
        if (!isExist) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, this.getClass().getSimpleName(), this.getId()));
        }
        return components.remove(index);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral peripheral1 : peripherals) {
            if (peripheral1.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
            }
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        boolean isExist = false;
        int index = 0;
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                isExist = true;
                break;
            }
            index++;
        }
        if (!isExist) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        return peripherals.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Component (%d):", components.size())).append(System.lineSeparator());
        for (Component component : components) {
            output.append("  " + component).append(System.lineSeparator());
        }
        double totalPerformance = peripherals.stream().mapToDouble(Product::getOverallPerformance).sum();
        output.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f)", peripherals.size(), totalPerformance / peripherals.size()));
        for (Peripheral peripheral : peripherals) {
            output.append("  " + peripheral).append(System.lineSeparator());
        }
        return super.toString();
    }
}

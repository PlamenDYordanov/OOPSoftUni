package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.BaseComputer;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    Collection<Computer> computers;
    Collection<Peripheral> peripherals;
    Collection<Component> components;

    public ControllerImpl() {
        components = new ArrayList<>();
        peripherals = new ArrayList<>();
        components = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {

        BaseComputer baseComputer = null;
        switch (computerType) {
            case "DesktopComputer":
                baseComputer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                baseComputer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        for (Computer computer : computers) {
            if (computer.getId() == baseComputer.getId()) {
                throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
            }
        }
        computers.add(baseComputer);
        return String.format(ADDED_COMPUTER,baseComputer.getId());
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        for (Peripheral currPeripheral : peripherals) {
                if (currPeripheral.getId() == peripheral.getId()) {
                    throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
                }

          Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == computerId).findFirst().orElse(null);
            currentComputer.addPeripheral(peripheral);
            this.peripherals.add(peripheral);
        }
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == computerId).findFirst().orElse(null);
        currentComputer.removePeripheral(peripheralType);
        Peripheral currentPeripherals = peripherals.stream().filter(peripheral -> peripheral.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
        peripherals.remove(currentPeripherals);
        return String.format(REMOVED_PERIPHERAL,peripheralType, currentPeripherals.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Component  component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        for (Component currComponent : components) {
            if (currComponent.getId() == component.getId()) {
                throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
            }
        }
        Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == computerId).findFirst().orElse(null);
        currentComputer.addComponent(component);
        components.add(component);

        return String.format(ADDED_COMPONENT,component.getClass().getSimpleName(), id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == computerId).findFirst().orElse(null);
        currentComputer.removeComponent(componentType);
        Component currentComponent = components.stream().filter(component -> component.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        components.remove(currentComponent);
        return String.format(REMOVED_COMPONENT,componentType, currentComponent.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == id).findFirst().orElse(null);
        return currentComputer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer bestComputer = computers.stream().max(Comparator.comparing(Computer::getOverallPerformance)).get();
        return null;
    }

    @Override
    public String getComputerData(int id) {
        Computer currentComputer = computers.stream().filter(computer1 -> computer1.getId() == id).findFirst().orElse(null);
        return currentComputer.toString();
    }
}

package aquarium;

import aquarium.core.Controller;
import aquarium.core.ControllerImpl;
import aquarium.core.Engine;
import aquarium.core.EngineImpl;


public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl();
        engine.run();
    }
}

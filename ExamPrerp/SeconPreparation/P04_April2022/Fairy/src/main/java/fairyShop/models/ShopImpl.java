package fairyShop.models;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {
        while (helper.canWork()) {
            if (present.isDone()){
                break;
            }
            for (Instrument instrument : helper.getInstruments()) {
                if (present.isDone()){
                    break;
                }
                while (!instrument.isBroken() && !present.isDone()) {
                    present.getCrafted();
                    helper.work();
                    instrument.use();
                }
            }
        }
    }
}

package fairyShop.models;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {

        while (helper.canWork() && present.isDone()) {
            helper.work();
            for (Instrument instrument : helper.getInstruments()) {
                if (present.isDone()) {
                    break;
                }
                while (!instrument.isBroken()) {
                    instrument.use();
                    present.getCrafted();
                    if (present.isDone()) {
                        break;
                    }
                }
            }
        }
    }
}


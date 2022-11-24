package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {
    private static final double HIGH_PRESSURE = Alarm.HIGH_PRESSURE_THRESHOLD + 1;
    private static final double LOW_PRESSURE = Alarm.LOW_PRESSURE_THRESHOLD - 1;
    private static final double CORRECT_PRESSURE = 20;


    @Test
    public void testAlarmSystemWhenPressureIsLow() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());

    }

    @Test
    public void testAlarmSystemWhenPressureIsHigh() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmWhenPressureIsCorrect() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(CORRECT_PRESSURE);
        Alarm alarm = new Alarm(sensor);
        Assert.assertFalse(alarm.getAlarmOn());
    }

}
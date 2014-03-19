package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;

public class RotaryPot {
    private final AnalogChannel m_pot;
    private static final RotaryPot inst = new RotaryPot();
    
    private RotaryPot() {
        m_pot = new AnalogChannel(RobotMap.ROTARY_POT_CHANNEL);
    }
    
    public static final RotaryPot getInstance() {
        return inst;
    }
    
    public double getAngle() {
        return (m_pot.getValue() * 0.3290322581 - 47.535483871);
    }
    
    public double getRaw() {
        return m_pot.getValue();
    }
    
    public double getVoltage() {
        return m_pot.getVoltage();
    }
}

package edu.nr.main.subsystems.TopArm;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;

public class ArmIR {
    private final DigitalInput m_ir;
    private final Counter m_counter;
    private static final ArmIR inst = new ArmIR();
    
    private ArmIR() {
        m_ir = new DigitalInput(RobotMap.TOP_ARM_IR_SENSOR);
        m_counter = new Counter(m_ir);
        m_counter.setMaxPeriod(250000);
    }
    
    public static final ArmIR getInstance() {
        return inst;
    }
    
    public boolean get() {
        return (!m_ir.get() && m_counter.getStopped());
    }
}

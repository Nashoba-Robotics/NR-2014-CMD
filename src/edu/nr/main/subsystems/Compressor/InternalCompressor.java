package edu.nr.main.subsystems.Compressor;

import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InternalCompressor extends CompressorBase implements Printable {
    private static InternalCompressor INSTANCE = null;
    
    private InternalCompressor() {
    }
    
    public void init(int spikePort) {
        m_spikePort = spikePort;
        spike.setDirection(Relay.Direction.kForward);
        
        limit.init();
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new InternalCompressorRun());
    }
    
    public void start() {
        spike.set(Relay.Value.kOn);
    }
    
    public void stop() {
        spike.set(Relay.Value.kOff);
    }
    
    public void run() {
        if(SmartDashboard.getBoolean("Auto Compressor")) {
            if(!limit.getLimit()) {
               start();
            }
            else {
              stop();
            }
        }
    }
    
    public boolean getLimit() {
        return limit.getLimit();
    }

    public void sendInfo() {
        SmartDashboard.putData("Internal Compressor Start", new InternalCompressorStart());
        SmartDashboard.putData("Internal Compressor Stop", new InternalCompressorStop());
        SmartDashboard.putData("Internal Compressor", this);
    }
    
    public static InternalCompressor getInstance() {
        if(INSTANCE == null) {
            synchronized(InternalCompressor.class) {
                if(INSTANCE == null) {
                    INSTANCE = new InternalCompressor();
                }
            }
        }
        return INSTANCE;
    }
}
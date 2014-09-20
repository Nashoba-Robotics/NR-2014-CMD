package edu.nr.main.subsystems.BottomRollers;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BottomRollers extends Subsystem
{
    private static BottomRollers singleton;
    public static BottomRollers getInstance()
    {
        if(singleton == null)
            singleton = new BottomRollers();
        
        return singleton;
    }
    
    
    private final RollerPair rollers;
    private BottomRollers()
    {
        CANJaguar roller1 = null;
        try 
        {
            roller1 = new CANJaguar(RobotMap.ROLLER_JAG);
        } 
        catch (CANTimeoutException ex) 
        {
            showCANException(ex);
        }
        Victor roller2 = new Victor(RobotMap.ROLLER_VICTOR);
        roller2.setSafetyEnabled(false);
        rollers = new RollerPair(roller1, roller2);
    }
    
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new RollIdleCommand());
    }
    
    public void startRoll()
    {
        rollers.start();
    }
    
    public void stopRoll()
    {
        rollers.stop();
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Bottom Rollers", this);
        SmartDashboard.putData("Roll Command", new RollCommand());
        SmartDashboard.putData("Stop Roll", new StopRollCommand());
    }
    
    /**
     * We are using a jaguar for one roller, and a victor for the other, so this class makes it easy
     * to set a speed for both. The jaguar and victor do not have the same voltage when told to run at
     * a certain percentage (one of them does not have a linear voltage vs. percent graph),
     * which is why the jaguar is set to 70% and the victor to 50%. The result from these percentages is roughly the same output voltage
     */
    private class RollerPair
    {
        private CANJaguar jag1;
        private Victor victor;
        public RollerPair(CANJaguar roller1, Victor roller2)
        {
            this.jag1 = roller1;
            this.victor = roller2;
        }
        
        public void start()
        {
            try 
            {
                jag1.setX(0.7);
            } 
            catch (CANTimeoutException ex) 
            {
                showCANException(ex);
            }
            victor.set(0.5);
        }
        
        public void stop()
        {
            try 
            {
                jag1.setX(0);
            } 
            catch (CANTimeoutException ex) 
            {
                showCANException(ex);
            }
            victor.set(0);
        }
        
        public CANJaguar getJag()
        {
            return jag1;
        }
        
        public Victor getVictor()
        {
            return victor;
        }
    }
    
    private static void showCANException(Exception ex)
    {
        Robot.reportCANException(ex, "Bottom Rollers Jag #" + RobotMap.ROLLER_JAG);
    }
}
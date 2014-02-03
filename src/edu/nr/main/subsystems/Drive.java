
package edu.nr.main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.nr.main.commands.DriveCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 */
public class Drive extends Subsystem 
{
    private RobotDrive drive = null;
    public Drive()
    {
        try
        {
            drive = new RobotDrive(new CANJaguar(1),new CANJaguar(2),new CANJaguar(3),new CANJaguar(4));
        }
        catch(CANTimeoutException e)
        {
            System.out.println("Couldn't create jags :(");
        }
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveCommand());
    }
    
    public void drive(double speed, double angle)
    {
        drive.drive(speed, angle);
    }
}


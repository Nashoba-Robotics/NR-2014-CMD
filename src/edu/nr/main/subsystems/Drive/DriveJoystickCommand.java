package edu.nr.main.subsystems.Drive;

import edu.nr.main.oi.OI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveJoystickCommand extends Command {
    private final Drive drv = Drive.getInstance();
    
    public DriveJoystickCommand() {
        requires(drv);
    }
    
    double x = 0, 
           y = 0, 
           lastEncoderDistance = 0;
    
    protected void initialize() {
        x = 0;
        y = 0;
        lastEncoderDistance = 0;
        drv.resetGyro();
    }
    
    protected void execute() {
        double ySpeed = OI.getJoy1Y();
        double zSpeed = OI.getJoy1Z();
        
        double angle = drv.getGyroAngle();
        angle = angle * (Math.PI / 180);
        
        double ave = drv.getAverageEncoderDistance() * (34d/32d);// * (34d/33d);
        double delta_x_r = (ave-lastEncoderDistance);
        double deltax = delta_x_r * Math.cos(-angle);
        double deltay = delta_x_r * Math.sin(-angle);
        x += deltax;
        y += deltay;
        
        lastEncoderDistance = ave;
        
        SmartDashboard.putNumber("Location x", x);
        SmartDashboard.putNumber("Location y", y);
        
        
        //SmartDashboard.putNumber("Y axis", ySpeed);
        //SmartDashboard.putNumber("Z axis", zSpeed);
        SmartDashboard.putNumber("Encoder 1", drv.getRawEncoder(1));
        SmartDashboard.putNumber("Encoder 2", drv.getRawEncoder(2));
        if(ySpeed < 0.05 && ySpeed > -0.05)
            ySpeed = 0;
        if(zSpeed < 0.05 && zSpeed > -0.05)
            zSpeed = 0;
        
        //Cut the speeds down because they are really fast
        zSpeed = (zSpeed/3) * 2;
        ySpeed = (ySpeed/4) * 3;
        
        drv.drive(ySpeed, zSpeed);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}

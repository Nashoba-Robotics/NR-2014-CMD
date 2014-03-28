/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class ShooterRotateTargetCommand extends Command
{
    private double speed;
    private double destination;
    boolean goingForward;
    private long startTime;
    
    public ShooterRotateTargetCommand(double destination)
    {
        this.speed = ShooterRotator.REGULAR_SPEED;
        this.destination = destination;
        this.requires(Robot.shooterRotator);
    }
    
    protected void initialize() 
    {
        Robot.shooterRotator.initCAN();
        
        startTime = System.currentTimeMillis();
        
        goingForward = (Robot.shooterRotator.getRotation() - destination) < 0;
    }

    int count = 0;
    protected void execute()
    {
        //Do all of the calculations in posotive, then apply negative sign at the end if we are going in reverse
        double err = Math.abs(destination - Robot.shooterRotator.getRotation());
        double proportionalStopDistance = 10;
        double proportionalSpeed = ((1/proportionalStopDistance)*err)*speed;
        double finalSpeed = Math.min(speed, proportionalSpeed);
        double integralSpeed = count * 0.004;
        
        finalSpeed += integralSpeed;
        finalSpeed *= ((goingForward)?1:-1); //If we are going in reverse, reverse the speed
        Robot.shooterRotator.rotate(finalSpeed);
        
        SmartDashboard.putNumber("Integral", integralSpeed);
        SmartDashboard.putNumber("Proportional", proportionalSpeed);
        SmartDashboard.putNumber("err", err);
        
        count++;
        //Robot.shooterRotator.rotate((goingForward)?speed:-speed);
    }

    protected boolean isFinished()
    {
        if(System.currentTimeMillis() - startTime > 5000)
        {
            return true;
        }
        
        if(goingForward)
        {
            //System.out.println("Quitting: " + Robot.shooterRotator.getRotation() + " forward");
            return Robot.shooterRotator.getRotation() - destination >= 0;
        }
        else
        {
            //System.out.println("Quitting: " + Robot.shooterRotator.getRotation() + " reverse");
            return Robot.shooterRotator.getRotation() - destination <= 0;
        }
    }

    protected void end()
    {
        Robot.shooterRotator.rotate(0);
    }

    protected void interrupted() {
    }
    
}

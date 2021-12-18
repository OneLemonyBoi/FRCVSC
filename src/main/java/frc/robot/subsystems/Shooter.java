package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // 2 Loading motors
    // 2 Shooting motors
    // 1 Steering motors

    private final TalonFX loadLeft;
    private final TalonFX loadRight;
    private final TalonFX shootLeft;
    private final TalonFX shootRight;
    private final TalonFX steer;

    public Shooter(int startPort) {
        loadLeft = new TalonFX(startPort++);
        loadRight = new TalonFX(startPort++);
        shootLeft = new TalonFX(startPort++);
        shootRight = new TalonFX(startPort++);
        steer = new TalonFX(startPort++);
    }

    public void homeToObject(LimeLight limeLight) {
        steer.set(TalonFXControlMode.PercentOutput.toControlMode(), (limeLight.getData()[1] / 27) * 0.2);
    }

    public void fireBall() {
        shootLeft.set(TalonFXControlMode.PercentOutput.toControlMode(), 1);
        shootRight.set(TalonFXControlMode.PercentOutput.toControlMode(), 1);
    }

    public void loadBall() {
        loadLeft.set(TalonFXControlMode.PercentOutput.toControlMode(), 0.5);
        loadRight.set(TalonFXControlMode.PercentOutput.toControlMode(), 0.5);
    }

    public boolean isCentered(LimeLight limeLight) {
        return limeLight.getData()[1] == 0;
    }
}
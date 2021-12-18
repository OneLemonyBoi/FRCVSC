package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final DifferentialDrive m_robotDrive;

    public DriveTrain() {
        m_robotDrive = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
    }

    public void arcadeDrive(DoubleSupplier UD, DoubleSupplier LR) {
        m_robotDrive.arcadeDrive(UD.getAsDouble(), LR.getAsDouble());
    }
}
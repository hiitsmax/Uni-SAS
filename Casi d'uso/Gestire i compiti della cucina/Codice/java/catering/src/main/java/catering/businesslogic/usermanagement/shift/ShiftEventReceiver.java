package catering.businesslogic.usermanagement.shift;

import catering.businesslogic.usermanagement.shift.Shift;
import catering.businesslogic.usermanagement.shift.ShiftEventReceiver;

public interface ShiftEventReceiver {
    public void updateShiftCreated(Shift sh);

    public void updateShiftDeleted(Shift sh);

    public void updateShiftModified(Shift sh);
}

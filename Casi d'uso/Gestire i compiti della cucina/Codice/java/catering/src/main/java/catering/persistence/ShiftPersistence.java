package catering.persistence;

import catering.businesslogic.usermanagement.shift.Shift;
import catering.businesslogic.usermanagement.shift.ShiftEventReceiver;

public class ShiftPersistence implements ShiftEventReceiver {
    public void updateShiftCreated(Shift sh) {
        Shift.saveNewShift(sh);
    }

    public void updateShiftDeleted(Shift sh) {
        Shift.deleteShift(sh);
    }

    public void updateShiftModified(Shift sh) {
        Shift.saveShift(sh);
    }
}

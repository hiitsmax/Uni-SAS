package catering.businesslogic.usermanagement.shift;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import catering.businesslogic.usermanagement.user.User;


public class ShiftManager {
    // Tutto creato da copilot, da rivedere
    private List<Shift> shiftList;

    public ShiftManager() {
        shiftList = new ArrayList<>();
    }

    public Shift createShift(Date start, Date end, ShiftType type, int limit) {
        Shift shift = new Shift(start, end, type, limit);
        shiftList.add(shift);
        notifyShiftCreated(shift);
        return shift;
    }

    public Shift modifyShift(Date start, Date end, ShiftType type, int limit) {
        Shift shift = new Shift(start, end, type, limit);
        notifyShiftModified(shift);
        return shift;
    }

    public void deleteShift(Date start, Date end, ShiftType type, int limit) {
        Shift shift = new Shift(start, end, type, limit);
        shiftList.remove(shift);
        notifyShiftRemoved(shift);
    }

    public void askStaffAvailability() {
        // Implementation for asking staff availability
    }

    public List<Shift> getShiftList(Date startDate, Date endDate) {
        List<Shift> filteredShifts = new ArrayList<>();
        for (Shift shift : shiftList) {
            if (shift.isInTimeframe(startDate, endDate)) {
                filteredShifts.add(shift);
            }
        }
        return filteredShifts;
    }

    public void makeUserAvailable(User user, Shift shift) {
        // Implementation for making a user available for a shift
    }

    public boolean isShiftFullInTimeframe(Shift shift, Date start, Date end) {
        // Implementation for checking if a shift is full within a timeframe
        return false;
    }

    private void notifyShiftRemoved(Shift shift) {
        // Implementation for notifying shift removal
    }

    private void notifyShiftCreated(Shift shift) {
        // Implementation for notifying shift creation
    }

    private void notifyShiftModified(Shift shift) {
        // Implementation for notifying shift modification
    }
}

package catering.businesslogic.usermanagement.shift;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import catering.businesslogic.usermanagement.user.User;
import catering.persistence.KitchenPersistence;


public class ShiftManager {
    // Tutto creato da copilot, da rivedere
    private static ArrayList<ShiftEventReceiver> shiftEventReceivers = new ArrayList<>();

    public ShiftManager() {
        Shift.loadAllShifts();
    }

    public Shift createShift(Date start, Date end, ShiftType type, int limit) {
        Shift shift = new Shift(start, end, type, limit);
        notifyShiftCreated(shift);
        return shift;
    }

    public Shift modifyShift(Date start, Date end, ShiftType type, int limit) {
        Shift shift = new Shift(start, end, type, limit);
        notifyShiftModified(shift);
        return shift;
    }

    public void deleteShift(Shift shift) {
        notifyShiftRemoved(shift);
    }

    public void askStaffAvailability() {
        // Implementation for asking staff availability
    }

    public List<Shift> getShiftList(java.util.Date startDate, java.util.Date endDate) {
        List<Shift> filteredShifts = new ArrayList<>();
        for (Shift shift : Shift.getLoadedShifts().values()) {
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
        for(ShiftEventReceiver shiftEventReceiver : shiftEventReceivers) {
            shiftEventReceiver.updateShiftDeleted(shift);
        }
    }

    private void notifyShiftCreated(Shift shift) {
        for(ShiftEventReceiver shiftEventReceiver : shiftEventReceivers) {
            shiftEventReceiver.updateShiftCreated(shift);
        }
    }

    private void notifyShiftModified(Shift shift) {
        for(ShiftEventReceiver shiftEventReceiver : shiftEventReceivers) {
            shiftEventReceiver.updateShiftModified(shift);
        }
    }

    public void addEventReceiver(ShiftEventReceiver shiftEventReceiver) {
        shiftEventReceivers.add(shiftEventReceiver);
    }
}

package catering.businesslogic.usermanagement.shift;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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

    public Shift getShiftByID(int id) {
        getShiftList(null, null);
        return shift[id];
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

    //TODO: Vedere come controllare se lo user è già impegnato in task o servizi di evento (e non preparazione)
    public Boolean isUserAvailable(User user, java.util.Date start, java.util.Date end) {
        // Implementation for checking if a user is available within a timeframe
        List<Shift> shiftInTimeFrame = getShiftList(start, end);


        ArrayList<TimeRange> timeToCover = new ArrayList<>();
        timeToCover.add(new TimeRange(start, end));

        for (Shift shift : shiftInTimeFrame) {
            if (shift.getAttendances().contains(user)) {
                ArrayList<TimeRange> timeToCoverCopy = new ArrayList<>(timeToCover);
                for (TimeRange timeRange : timeToCoverCopy) {
                    if (shift.getStart().compareTo(timeRange.start) <= 0 && shift.getEnd().compareTo(timeRange.end) >= 0) {
                        timeToCover.remove(timeRange);
                    } else if (shift.getStart().compareTo(timeRange.start) <= 0 && shift.getEnd().compareTo(timeRange.end) <= 0) {
                        timeRange.start = shift.getEnd();
                    } else if (shift.getStart().compareTo(timeRange.start) >= 0 && shift.getEnd().compareTo(timeRange.end) >= 0) {
                        timeRange.end = shift.getStart();
                    } else if (shift.getStart().compareTo(timeRange.start) >= 0 && shift.getEnd().compareTo(timeRange.end) <= 0) {
                        timeToCover.remove(timeRange);
                        timeToCover.add(new TimeRange(timeRange.start, shift.getStart()));
                        timeToCover.add(new TimeRange(shift.getEnd(), timeRange.end));
                    }
                }
            }
        }

        // Print remaining time ranges
        for (TimeRange timeRange : timeToCover) {
            System.out.println("Remaining Time Range: " + timeRange.start + " - " + timeRange.end);
        }

        return timeToCover.isEmpty();
    }

        class TimeRange {
            public java.util.Date start;
            public java.util.Date end;

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((start == null) ? 0 : start.hashCode());
                result = prime * result + ((end == null) ? 0 : end.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                TimeRange other = (TimeRange) obj;
                if (start == null) {
                    if (other.start != null)
                        return false;
                } else if (!start.equals(other.start))
                    return false;
                if (end == null) {
                    if (other.end != null)
                        return false;
                } else if (!end.equals(other.end))
                    return false;
                return true;
            }

            public TimeRange(java.util.Date start2, java.util.Date end2) {
                this.start = start2;
                this.end = end2;
            }
        }
}


package catering.businesslogic.kitchenmanagement.summarysheet;

import java.util.ArrayList;

import catering.businesslogic.eventmanagement.service.Service;
import catering.businesslogic.kitchenmanagement.task.Task;
import catering.businesslogic.kitchenmanagement.task.TaskListOrder;


public class SummarySheet {
    private TaskListOrder order;
    private boolean editable;
    private Service service;

    public SummarySheet create(boolean order, boolean editable) {
        // Method implementation
        return null;
    }

    public void setOrder(TaskListOrder order) {
        // Method implementation
    }

    public void orderTaskList(TaskListOrder order) {
        // Method implementation
    }

    public void setEditable(boolean flag) {
        // Method implementation
    }

    public void addTaskToSheet(Task t) {
        // Method implementation
    }

    public ArrayList<Task> getTaskList() {
        // Method implementation
        return null;
    }

    public void setTaskList() {
        // Method implementation
    }

    public void move(int index, Task t) {
        // Method implementation
    }

    public void setService(Service s) {
        this.service = s;
    }
}

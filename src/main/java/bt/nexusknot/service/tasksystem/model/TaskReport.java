package bt.nexusknot.service.tasksystem.model;

import java.util.Date;

public class TaskReport
{
    private TaskStatus status;
    private int secondsConsumed;
    private Date startDate;
    private Date endDate;
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public TaskStatus getStatus()
    {
        return status;
    }

    public void setStatus(TaskStatus status)
    {
        this.status = status;
    }

    public int getSecondsConsumed()
    {
        return secondsConsumed;
    }

    public void setSecondsConsumed(int secondsConsumed)
    {
        this.secondsConsumed = secondsConsumed;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
}

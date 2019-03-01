package bt.nexusknot.service.tasksystem.model;

import bt.nexusknot.service.tasksystem.boundary.Task;

import java.util.Date;

public class Heartbeat extends Task<Heartbeat>
{
    private TaskReport report = null;

    public Heartbeat()
    {
        this.report = new TaskReport();
        this.report.setStatus(TaskStatus.PREPARATION);
        this.setName("Heartbeat Test 00103");
    }


    public Heartbeat prepare()
    {

        this.report.setStartDate(new Date());
        this.report.setStatus(TaskStatus.AWAIT_EXECUTION);
        return this;
    }

    public TaskReport getReport()
    {
        return this.report;
    }


    @Override
    public void run()
    {
        this.report.setStatus(TaskStatus.EXECUTION);

        this.report.setStatus(TaskStatus.SUCCESS);
    }
}

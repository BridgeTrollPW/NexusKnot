package bt.nexusknot.service.tasksystem.boundary;

import bt.nexusknot.service.tasksystem.model.TaskReport;

public abstract class Task<T extends Task<T>> implements Runnable
{
    protected String name;
    protected String description;
    /**
     * @return Task
     */
    public abstract T prepare();

    public abstract TaskReport getReport();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

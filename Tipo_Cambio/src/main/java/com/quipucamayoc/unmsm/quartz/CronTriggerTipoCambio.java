package com.quipucamayoc.unmsm.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
public class CronTriggerTipoCambio {

    public void iniciar() {
        JobDetail job = JobBuilder.newJob(TipoCambioJob.class)
                .withIdentity("dummyJobNameTipo", "group2").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerNameTipo", "group2")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 30 03 * * ?"))
                .build();

        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

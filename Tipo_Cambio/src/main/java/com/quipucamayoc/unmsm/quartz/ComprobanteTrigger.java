package com.quipucamayoc.unmsm.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ComprobanteTrigger {
    public void iniciar() {
        JobDetail job = JobBuilder.newJob(ComprobanteJob.class)
                .withIdentity("dummyJobNameComprobante", "group3").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerNameComprobante", "group3")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 00 01 * * ?"))
                .build();

        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
        }
        try {
            scheduler.start();
        } catch (SchedulerException e) {
        }
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
        }
    }
}

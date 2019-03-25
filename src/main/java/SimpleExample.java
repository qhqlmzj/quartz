import com.mascode.demo1.HelloJob;
import org.quartz.JobDetail;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleExample {


    public void run() throws Exception {


        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        StdScheduler sched = (StdScheduler) sf.getScheduler();



        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .storeDurably()
                .build();

        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .forJob(job)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(2))
                .build();

        Trigger trigger2 = newTrigger()
                .withIdentity("trigger2", "group2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(5))
                .forJob(job)
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.addJob(job, true);
        sched.scheduleJob(trigger);
        sched.scheduleJob(trigger2);


        // Start up the scheduler (nothing can actually run until the 
        // scheduler has been started)
        sched.start();


        // wait long enough so that the scheduler as an opportunity to 
        // run the job!
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
        }

        // shut down the scheduler
        sched.shutdown(true);
    }

    public static void main(String[] args) throws Exception {

        SimpleExample example = new SimpleExample();
        example.run();
    }
}
一个简单的调度任务 http://spring.io/guides/gs/scheduling-tasks/ <br>
SchedulingTasks.kt使用@Component注解，相当于在SpringConfig文件中配置<bean id="", class="SchedulingTasks"/>，简单地装配该类。<br>
reportCurrentTime方法使用了@Scheduled注解,在设定的时间调用该方法。同时，需要在程序入口Application类上添加@EnableScheduling注解

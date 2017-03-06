package hello

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by tao on 17-3-6.
 */

@Component
open class ScheduledTasks {
    private val log = getLogger(javaClass)
    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    @Scheduled(fixedRate = 5000)
    fun reportCurrentTime(){
        log.info("The time is now {}", dateFormat.format(Date()))
    }
}
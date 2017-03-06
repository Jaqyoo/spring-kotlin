package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Created by tao on 17-3-6.
 */
@SpringBootApplication
@EnableScheduling
open class Application {
}

fun main(args: Array<String>){
    SpringApplication.run(Application::class.java, *args)
}
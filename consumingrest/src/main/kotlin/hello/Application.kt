package hello

import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.awt.SystemColor.info
import org.springframework.boot.CommandLineRunner



/**
 * Created by tao on 17-3-6.
 */
@SpringBootApplication
open class Application {
    val log = getLogger(javaClass)

    @Bean
    open fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
        return builder.build()
    }

    @Bean
    @Throws(Exception::class)
    open fun run(restTemplate: RestTemplate): CommandLineRunner {
        return CommandLineRunner{
            val quote = restTemplate.getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)
            log.info(quote.toString())
        }
    }
}

fun main(args: Array<String>){
    SpringApplication.run(Application::class.java)
}
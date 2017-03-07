package hello

import hello.storage.StorageProperties
import hello.storage.StorageService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Created by tao on 17-3-7.
 */

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties::class)
open class Application {
    @Bean
    open fun init(storageService: StorageService): CommandLineRunner {
        return CommandLineRunner{
            storageService.deleteAll()
            storageService.init()
        }
    }
}

fun main(args: Array<String>){
    SpringApplication.run(Application::class.java, *args)
}
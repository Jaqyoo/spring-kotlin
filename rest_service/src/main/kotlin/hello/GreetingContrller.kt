package hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

/**
 * Controller
 * Created by tao on 17-3-6.
 */
@RestController
class GreetingController {
    val template = "Hello, %s"
    val counter = AtomicLong()

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name:String): Greeting {
        return Greeting(counter.incrementAndGet(), String.format(template, name))
    }
}
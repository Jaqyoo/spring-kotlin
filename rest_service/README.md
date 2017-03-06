Spring官方教程 http://spring.io/guides/gs/rest-service/ 的Kotlin实现

* 模型层：Greeting.kt
  包含一个简单Kotlin对象（POKO?)。
* 控制层：GreetingController.kt
  使用@RestController注解该类（自Spring4.0开始使用，相当于@Controller+@Responsebody）（教程上无需设置其参数Value，The value may indicate a suggestion for a logical component name, to be turned into a Spring bean in case of an autodetected component.）<br>
  使用@RequestMapping注解其中的方法，重定向页面

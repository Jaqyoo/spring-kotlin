Spring官方教程 http://spring.io/guides/gs/rest-service/ 的Kotlin实现
* 效果：简单的Restful服务，当服务器接收到来自/greeting页面的访问请求时，返回json-api: {"id":$name,"content":"Hello,World"} 其中$name为访问次数
* 模型层：Greeting.kt
  包含一个简单Kotlin对象（POKO?)。
* 控制层：GreetingController.kt
  使用@RestController注解该类（自Spring4.0开始使用，相当于@Controller+@Responsebody）（教程上无需设置其参数Value，The value may indicate a suggestion for a logical component name, to be turned into a Spring bean in case of an autodetected component.）<br>
  使用@RequestMapping注解其中的方法，重定向页面
 * 程序入口Application.kt
  Kotlin语言与Java语言有区别，main方法在类外面写。使用@SpringBootApplication注解Application类（需加上open属性，因kotlin默认为final类）

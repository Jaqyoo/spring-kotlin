package hello

import hello.storage.StorageService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.multipart.MultipartFile

/**
 * Created by tao on 17-3-7.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class FileUploadIntegrationTests {
    @Autowired
    var restTemplate: TestRestTemplate? = null

    @MockBean
    var storageService:StorageService ?= null

    @LocalServerPort
    var port: Int? = null

    @Test
    fun shouldUploadFile(){
        val resource = ClassPathResource("testupload.txt", javaClass)
        val map :MultiValueMap<String, Any> = LinkedMultiValueMap()
        map.add("file", resource)
        val response: ResponseEntity<String>? = this.restTemplate?.postForEntity("/", map, String::class.java)

        assertThat(response?.statusCode).isEqualByComparingTo(HttpStatus.FOUND)
        assertThat(response?.headers?.location.toString()).startsWith("http://localhost:${this.port}/")
        storageService?.store(Matchers.any(MultipartFile::class.java))
        then(storageService).should()?.store(Matchers.any(MultipartFile::class.java))
    }

    @Test
    fun shouldDownloadFile(){
        val resource = ClassPathResource("testupload.txt", javaClass)
        given(this.storageService?.loadAsResource("testupload.txt")).willReturn(resource)

        val response1:ResponseEntity<String> ?= this.restTemplate?.getForEntity("/files/{filename}", String::class.java, "testupload.txt")
        assertThat(response1?.statusCode).isEqualTo(200)
        assertThat(response1?.headers?.getFirst(HttpHeaders.CONTENT_DISPOSITION)).isEqualTo("attachment; filename=\"testupload.txt\"")
        assertThat(response1?.body).isEqualTo("Spring Framework")
    }
}
package hello.storage

import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream
import javax.annotation.Resource


/**
 * Created by tao on 17-3-7.
 */
interface StorageService {
    fun init()

    fun store(file: MultipartFile)

    fun loadAll(): Stream<Path>

    fun load(filename: String): Path

    fun loadAsResource(filename: String): org.springframework.core.io.Resource

    fun deleteAll()
}
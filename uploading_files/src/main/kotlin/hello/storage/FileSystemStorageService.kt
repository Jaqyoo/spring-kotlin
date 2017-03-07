package hello.storage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

/**
 * Created by tao on 17-3-7.
 */
@Service
open class FileSystemStorageService :StorageService {
    var rootLocation: Path? = null

    @Autowired
    constructor(properties: StorageProperties){
        this.rootLocation = Paths.get(properties.location)
    }

    override fun store(file: MultipartFile) {
        try {
            if (file.isEmpty){
                throw StorageException("Failed to store empty file ${file.originalFilename}")
            }
            Files.copy(file.inputStream, this.rootLocation?.resolve(file.originalFilename))
        } catch (e:IOException){
            throw StorageException("Failed to store file ${file.originalFilename}", e)
        }
    }

    override fun load(filename: String): Path {
        return rootLocation!!.resolve(filename)
    }

    override fun loadAll(): Stream<Path> {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter { it != this.rootLocation }
                    .map { this.rootLocation!!.relativize(it) }
        } catch (e:IOException){
            throw StorageException("Failed to read stored files", e)
        }
    }

    override fun loadAsResource(filename: String): Resource {
        try {
            val resource = UrlResource(load(filename).toUri())
            if (resource.exists() || resource.isReadable){
                return resource
            } else {
                throw StorageFileNotFoundException("Could not read file: ${filename}")
            }
        } catch (e:MalformedURLException){
            throw StorageFileNotFoundException("Counld not read file: ${filename}", e)
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation!!.toFile())
    }

    override fun init() {
        try {
            Files.createDirectory(rootLocation)
        } catch (e: IOException){
            throw StorageException("Could not initialize storage", e)
        }
    }

}
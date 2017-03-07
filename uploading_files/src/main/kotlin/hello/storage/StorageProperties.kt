package hello.storage

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Created by tao on 17-3-7.
 */
@ConfigurationProperties("storage")
open class StorageProperties {

    /**
     * Folder location for storing files
     */
    var location = "upload-dir"

}
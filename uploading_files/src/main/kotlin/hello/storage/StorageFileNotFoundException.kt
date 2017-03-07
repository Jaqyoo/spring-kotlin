package hello.storage

/**
 * Created by tao on 17-3-7.
 */
open class StorageFileNotFoundException : StorageException {

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
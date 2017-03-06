package hello

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by tao on 17-3-6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)


class Value {
    var id: Long? = null
    var quote: String? = null

    override fun toString(): String {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}'
    }
}
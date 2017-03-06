package hello

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by tao on 17-3-6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Quote {
    var type: String? = null
    var value: Value? = null

    override fun toString(): String {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}'
    }
}
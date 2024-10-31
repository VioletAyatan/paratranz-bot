package com.example.tools

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream
import java.io.OutputStream

/**
 * Jackson工具类
 *
 * Jackson是一个流行的Json库，这个工具封装了一些简单的方法实现常用的json转换
 * @author lck
 */
object JacksonUtil {
    private val objectMapper = jacksonObjectMapper().apply {
        //用于确定遇到未知属性时，是否应导致失败（抛出 JsonMappingException）
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }

    /**
     * 将对象序列化为 JSON 字符串格式。
     *
     * 该方法适用于将对象转换为 JSON 字符串，以便在应用程序中进一步处理或输出。
     *
     * @param obj 要序列化为 JSON 字符串的对象。
     * @return 表示该对象的 JSON 字符串。
     */
    fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    /**
     * 将对象序列化为 JSON 格式并直接写入指定的 [OutputStream]
     *
     * 该方法适用于将 JSON 数据流式传输到外部目标（如文件、网络）。
     *
     * @param obj 要序列化为 JSON 格式的对象。
     * @param outputStream 用于写入 JSON 数据的 OutputStream。
     */
    fun toJson(obj: Any, outputStream: OutputStream) {
        return objectMapper.writeValue(outputStream, obj)
    }

    /**
     * Json转对象
     * @param json json字符串
     * @param target 对象class
     */
    fun <T> fromJson(json: String, target: Class<T>): T {
        return objectMapper.readValue(json, target)
    }

    /**
     * Json转对象
     * @param bytes byte数组
     * @param target 对象class
     */
    fun <T> fromJson(bytes: ByteArray, target: Class<T>): T {
        return objectMapper.readValue(bytes, target)
    }

    /**
     * Json转对象
     * @param inputStream 输入流
     * @param target 对象class
     */
    fun <T> fromJson(inputStream: InputStream, target: Class<T>): T {
        return objectMapper.readValue(inputStream, target)
    }

    /**
     * Json转对象
     *
     * 对于带泛型的对象请使用此方法，使用[TypeReference]传入明确的类型
     *
     * Java示例：
     * ```java
     * //创建TypeReference
     * new TypeReference<List<String>>(){}
     * ```
     * Kotlin示例：
     * ```kotlin
     * //使用更加简单的包装函数
     * val type = jacksonTypeRef<List<String>>()
     * ```
     * @param json json字符串
     * @param typeReference 类型引用，参考：[TypeReference]
     */
    fun <T> fromJson(json: String, typeReference: TypeReference<T>): T {
        return objectMapper.readValue(json, typeReference)
    }

    /**
     * 验证json格式是否合法
     * @param json json字符串
     * @return [Boolean] json格式是否合法
     */
    fun validateJson(json: String): Boolean {
        try {
            if (json.isNotEmpty()) {
                objectMapper.readTree(json)
                return true
            }
            return false
        } catch (e: JsonProcessingException) {
            return false
        }
    }

    /**
     * 获取工具类使用的ObjectMapper
     * @return [ObjectMapper]
     */
    fun getObjectMapper() = objectMapper

    /**
     * Json转为JsonTree
     * @return [JsonNode]
     */
    fun toTree(json: String): JsonNode {
        return objectMapper.readTree(json)
    }

    /**
     * javaBean转成JsonTree
     * @return [JsonNode]
     */
    fun <T : JsonNode> toTree(obj: Any): T {
        return objectMapper.valueToTree(obj)
    }
}
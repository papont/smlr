package ru.sam.smlr.service

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by user on 16.08.2017.
 */
@Component
class DefaultKeyMapperService: KeyMapperService{

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        if(map.contains(key)) {
            return KeyMapperService.Add.AlreadyExist(key)
        } else {
            map.put(key, link)
            return KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String) = if(map.contains(key)) {
        KeyMapperService.Get.Link(map.get(key)!!)
    } else {
        KeyMapperService.Get.NotFound(key)
    }
}
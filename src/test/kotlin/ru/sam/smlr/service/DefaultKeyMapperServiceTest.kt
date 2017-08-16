package ru.sam.smlr.service

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by user on 16.08.2017.
 */

class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aAbBcCdD"

    private val LINK: String = "http://yandex.ru"
    private val LINK_NEW: String = "http://www.eveonline.com"

    @Test
    fun clientCanAddNewKeyWithLink() {
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotAddExistingKey() {
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotTakeLinkIfKeyNotFoundInService() {
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}

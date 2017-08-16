package ru.sam.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.sam.smlr.service.KeyMapperService
import javax.servlet.http.HttpServletResponse

/**
 * Created by user on 16.08.2017.
 */

@Controller
@RequestMapping("/{key}")
class RedirectController {

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping()
    fun redirect(@PathVariable("key") key: String, responce: HttpServletResponse) {
        val result = service.getLink(key)
        when (result) {
            is KeyMapperService.Get.Link -> {
                responce.setHeader(HEADER_NAME, HEADER_VALUE)
                responce.status = REDIRECT_STATUS
            }

            is KeyMapperService.Get.NotFound -> {
                responce.status = 404
            }
        }
    }

    companion object {
        private val HEADER_NAME = "Location"
        private val PATH = "aAbBcCdD"
        private val REDIRECT_STATUS: Int = 302
        private val HEADER_VALUE = "http://www.eveonline.com"

    }
}
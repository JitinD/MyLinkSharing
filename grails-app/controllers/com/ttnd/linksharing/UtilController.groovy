package com.ttnd.linksharing

import groovy.util.logging.Log4j

@Log4j
class UtilController {

    def index()
    {
        log.fatal("Fatal log entry")
        log.error("Error log entry")
        log.info("Info log entry")
        log.warn("Warn log entry")
    }
}

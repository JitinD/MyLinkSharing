package CO

import grails.validation.Validateable

@Validateable
class SearchCO {
    String q
    Integer max
    Integer offset
    String order
    String sort
}

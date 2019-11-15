package com.amrhal.movielix.domain.gateways


object CashingGateway {

    const val MAX_COUNT_OF_PAGER_ADAPTOR:Int = 4

    var lastSelectedCategoryPos:Int = 0

    var rvPositionforEachPage = IntArray(MAX_COUNT_OF_PAGER_ADAPTOR)

}
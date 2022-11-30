package com.nst.tutorials.rest.contractvalidation;

import com.ebay.nst.NSTServiceTestRunner;
import com.ebay.nst.NSTServiceWrapperProcessor;
import com.ebay.softassert.EbaySoftAssert;
import com.nst.tutorials.rest.CanadaHoliday;
import org.testng.annotations.Test;

public class ContractValidationTest implements NSTServiceTestRunner {

    private final NSTServiceWrapperProcessor serviceWrapperProcessor = new NSTServiceWrapperProcessor();

    @Test
    public void exampleRestTest() throws Exception {
        // Send a GET /api/v1/holidays/{holidayId} request.
        ContractValidationWrapper restServiceWrapper = new ContractValidationWrapper(CanadaHoliday.CANADA_DAY);
        serviceWrapperProcessor.sendRequestAndGetJSONResponse(restServiceWrapper);
    }

    @Test
    public void exampleRestTestWithSchemaValidationError() throws Exception {
        // Send a GET /api/v1/holidays/{holidayId} request and observe the schema validation error.
        ContractValidationErrorWrapper restServiceWrapper = new ContractValidationErrorWrapper(CanadaHoliday.CANADA_DAY);
        serviceWrapperProcessor.sendRequestAndGetJSONResponse(restServiceWrapper);
    }

    @Test
    public void exampleRestTestWithPolymorphicSchemaValidationError() throws Exception {
        // Send a GET /api/v1/holidays/{holidayId} request and observe the polymorphic schema validation error.
        ContractValidationPolymorphicErrorWrapper restServiceWrapper = new ContractValidationPolymorphicErrorWrapper(CanadaHoliday.CANADA_DAY);
        serviceWrapperProcessor.sendRequestAndGetJSONResponse(restServiceWrapper);
    }

    @Override
    public EbaySoftAssert getSoftAssert() {
        return null;
    }
}

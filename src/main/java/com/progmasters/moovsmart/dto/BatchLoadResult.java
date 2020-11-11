package com.progmasters.moovsmart.dto;

import java.util.ArrayList;
import java.util.List;

public class BatchLoadResult {
    private List<BatchLoadResultItem> resultItems = new ArrayList<>();
    private String generalError;

    public BatchLoadResult() {
    }

    public BatchLoadResult(String generalError) {
        this.generalError = generalError;
    }

    public String getGeneralError() {
        return generalError;
    }

    public void setGeneralError(String generalError) {
        this.generalError = generalError;
    }

    public List<BatchLoadResultItem> getResultItems() {
        return resultItems;
    }

    public void setResultItems(List<BatchLoadResultItem> resultItems) {
        this.resultItems = resultItems;
    }
}

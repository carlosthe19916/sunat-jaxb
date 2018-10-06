package io.github.carlosthe19916.sunatjaxb.mappers;

import io.github.carlosthe19916.sunatjaxb.beans.beans21.Invoice21Bean;

public class MockInvoice21Mapper implements Invoice21Mapper {

    @Override
    public Invoice21Bean map(Invoice21Bean invoice21Bean) {
        return null;
    }

    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public int order() {
        return 1;
    }

}

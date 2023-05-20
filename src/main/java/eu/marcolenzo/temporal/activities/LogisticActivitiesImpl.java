package eu.marcolenzo.temporal.activities;

import eu.marcolenzo.temporal.model.Order;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class LogisticActivitiesImpl implements LogisticActivities {
    @Override
    public String bookShipping(Order order) {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}

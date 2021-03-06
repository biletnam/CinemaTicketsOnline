package com.netcracker.cinema.web.admin.rating;

import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import org.tepi.filtertable.FilterGenerator;

import java.io.Serializable;

@SuppressWarnings("serial")
class DemoFilterGenerator implements FilterGenerator, Serializable {

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        System.err.println("Custom Filter Requested (with value) for PropId: "
                + propertyId);
        if ("id".equals(propertyId)) {
            /* Create an 'equals' filter for the ID field */
            if (value != null && value instanceof String) {
                try {
                    return new Compare.Equal(propertyId,
                            Integer.parseInt((String) value));
                } catch (NumberFormatException ignored) {
                    // If no integer was entered, just generate default filter
                }
            }
        } else if ("checked".equals(propertyId)) {
            if (value != null && value instanceof Boolean) {
                if (Boolean.TRUE.equals(value)) {
                    return new Compare.Equal(propertyId, value);
                } else {
                    return new Or(new Compare.Equal(propertyId, true),
                            new Compare.Equal(propertyId, false));
                }
            }
        }
        // For other properties, use the default filter
        return null;
    }


    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        System.err.println("Custom Filter Component Requested for PropId: " + propertyId);
        // removed custom filter component for id
        if ("checked".equals(propertyId)) {
            CheckBox box = new CheckBox();
            return box;
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        Notification n = new Notification("Filter removed from: " + propertyId,
                Notification.Type.TRAY_NOTIFICATION);
        n.setDelayMsec(800);
        n.show(Page.getCurrent());
    }

    @Override
    public void filterAdded(Object propertyId,
                            Class<? extends Container.Filter> filterType, Object value) {
        Notification n = new Notification("Filter added to: " + propertyId,
                Notification.Type.TRAY_NOTIFICATION);
        n.setDelayMsec(800);
        n.show(Page.getCurrent());
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        System.err.println("Custom Filter Requested (with field) for PropId: " + propertyId);
        // Use the default filter
        return null;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId,
                                                  Object value) {
        /* Return null -> Does not add any filter on failure */
        return null;
    }
}

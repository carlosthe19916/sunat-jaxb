package io.github.carlosthe19916.sunatjaxb.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static XMLGregorianCalendar toGregorianCalendar(Date date, TimeZone timeZone) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(timeZone);
            String locale = sdf.format(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(locale);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public static XMLGregorianCalendar toGregorianCalendarTime(Date date, TimeZone timeZone) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(timeZone);
            String locale = sdf.format(date);
            XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(locale);
            return xmlCal;
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

}

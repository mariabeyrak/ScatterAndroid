package com.mariabeyrak.scatter.models.requests.Transaction.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionRequestParams {
    private Transaction transaction;
    private Buf buf;

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public TransactionRequestParams(Transaction transaction, Buf buf) {
        super();
        this.transaction = transaction;
        this.buf = buf;
    }

    public long getEstimatedInSeconds() {
        try {
            Date date = DATE_FORMAT.parse(transaction.getExpiration());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date.getTime());
            calendar.add(Calendar.MILLISECOND,
                    calendar.getTimeZone().getRawOffset() + calendar.getTimeZone().getDSTSavings());
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            return 0;
        }
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Buf getBuf() {
        return buf;
    }

    @Override
    public String toString() {
        return "TransactionRequestParams{" +
                "transaction=" + transaction +
                ", buf=" + buf +
                '}';
    }
}



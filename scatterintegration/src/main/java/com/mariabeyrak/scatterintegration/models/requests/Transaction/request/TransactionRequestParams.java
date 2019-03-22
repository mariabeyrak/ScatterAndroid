package com.mariabeyrak.scatterintegration.models.requests.Transaction.request;

import com.mariabeyrak.scatterintegration.models.requests.ScatterRequestParams;
import com.paytomat.eos.PrivateKey;
import com.paytomat.eos.transaction.EosAction;
import com.paytomat.eos.transaction.EosExtentionType;
import com.paytomat.eos.transaction.EosTransaction;

import org.bouncycastle.util.encoders.Hex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionRequestParams extends ScatterRequestParams {
    private Transaction transaction;
    private Buf buf;

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public TransactionRequestParams(Transaction transaction, Buf buf) {
        super();
        this.transaction = transaction;
        this.buf = buf;
    }

    private long getEstimatedInSeconds() {
        try {
            Date date = DATE_FORMAT.parse(transaction.getExpiration());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date.getTime());
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getRawOffset());
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            return 0;
        }
    }

    private String getChainIdHex() {
        byte[] byteArray = new byte[32];
        System.arraycopy(buf.getData(), 0, byteArray, 0, 32);
        return Hex.toHexString(byteArray);
    }

    public EosTransaction toEosTransaction(PrivateKey privateKey) {
        return new EosTransaction(
                privateKey, this.getChainIdHex(), getEstimatedInSeconds(),
                (short) transaction.getRefBlockNum(), (int) transaction.getRefBlockPrefix(),
                transaction.getMaxNetUsageWords(), (byte) transaction.getMaxCpuUsageMs(),
                transaction.getDelaySec(), new EosAction[]{}, transaction.getEosActions(), new EosExtentionType[]{}
        );
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



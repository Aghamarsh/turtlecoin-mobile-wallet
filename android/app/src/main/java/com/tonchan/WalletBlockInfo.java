package com.tonchan;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

public class WalletBlockInfo {
    RawTransaction coinbaseTransaction;
    RawTransaction[] transactions;
    long blockHeight;
    String blockHash;
    long blockTimestamp;

    public WalletBlockInfo() {}

    public WalletBlockInfo(
        RawTransaction coinbaseTransaction,
        RawTransaction[] transactions,
        long blockHeight,
        String blockHash,
        long blockTimestamp) {
        this.coinbaseTransaction = coinbaseTransaction;
        this.transactions = transactions;
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.blockTimestamp = blockTimestamp;
    }

    public WalletBlockInfo(ReadableMap map) {
        this(
            new RawTransaction(map.getMap("coinbaseTransaction")),
            RawTxVector(map.getArray("transactions")),
            (long)map.getDouble("blockHeight"),
            map.getString("blockHash"),
            (long)map.getDouble("blockTimestamp")
        );
    }

    private static RawTransaction[] RawTxVector(ReadableArray arr) {
        RawTransaction[] txs = new RawTransaction[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            txs[i] = new RawTransaction(arr.getMap(i));
        }

        return txs;
    }
}

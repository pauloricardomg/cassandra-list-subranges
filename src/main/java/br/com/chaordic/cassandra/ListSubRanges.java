package br.com.chaordic.cassandra;

import java.util.List;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfSplit;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ListSubRanges {

    public static void main(String[] args) throws Exception {
        if (args.length != 6){
            System.err.println("Usage: java -jar list-subranges.jar <host> <ks> <cf> <keysPerSplit> <startToken> <endToken>");
            System.exit(1);
        }

        String host = args[0];
        String ks = args[1];
        String cf = args[2];
        int keysPerSplit = Integer.valueOf(args[3]);
        String startToken = args[4];
        String endToken = args[5];

        TTransport tr = new TFramedTransport(new TSocket(host, 9160));
        TProtocol proto = new TBinaryProtocol(tr);
        Cassandra.Client client = new Cassandra.Client(proto);
        tr.open();

        client.set_keyspace(ks);
        List<CfSplit> cfSplits = client.describe_splits_ex(cf, startToken, endToken, keysPerSplit);
        System.out.println("Start Token                             End Token                               Estimated Size");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (CfSplit cfSplit : cfSplits) {
            System.out.printf("%s %s %d\n", cfSplit.start_token, cfSplit.end_token, cfSplit.row_count);
        }
    }

}

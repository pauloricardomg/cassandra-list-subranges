cassandra-list-subranges
===

Lists token subtranges of a given column family. The output of this command can be used as input for subrange repair.

For more information on subrange repair, check this link: http://www.datastax.com/dev/blog/advanced-repair-techniques

# Dependencies

* Maven

# Compile

Before using the tool, compile it using the following command:

`mvn package`

## Supported Cassandra versions

The project is configured by default to use Cassandra 1.2.16.

In order to compile for Cassandra 2.0.X, modify the `<version>` attribute on the "org.apache.cassandra" pom.xml dependency.

# Usage

```
usage: java -jar list-subranges.jar <nodeIpAddress> <keySpace> <columnFamily>

Lists CF subranges for a particular node or token range.

The output of this command can be used as input for subrange repair.
 -et,--end-token <arg>       Calculate subranges of the range with this
                             end token.
 -n,--num-partitions <arg>   Number of partitions per subsplit. (default
                             32K)
 -o,--omit-header            Number of partitions per subsplit.
 -pr,--partitioner-range     Only consider the first range returned by the
                             partitioner.
 -st,--start-token <arg>     Calculate subranges of the range with this
                             start token.
```

## Example

```
java -jar target/cassandra-list-subtranges-0.0.2-SNAPSHOT.jar 55.233.44.168 myks mycf

Start Token                             End Token                               Estimated Size
------------------------------------------------------------------------------------------------
74436767763955288882613195375699296256  74439973730150275837031561482829976149  32768
74439973730150275837031561482829976149  74443147299065436464785732203520768529  32768
74443147299065436464785732203520768529  74446308864678424144281808412855325434  32768
74446308864678424144281808412855325434  74449503757202033309267324422423573442  32768
74449503757202033309267324422423573442  74452743303842114263527346967989205341  32768
74452743303842114263527346967989205341  74455939694977870753656687218158491917  32768
74455939694977870753656687218158491917  74459152093479228618429026679825938232  32768
74459152093479228618429026679825938232  74462336296486462312787078846957455213  32768
```

# Subrange repair

Use the output of the cassandra-list-subtranges as input for the repair command:

`nodetool repair <ks> <cf> -st <startToken> -et <endToken>`

## Example

`nodetool repair myks mycf -st 154281943151324548043651808333706143715 -et 156541576535381173370881008574335517403`

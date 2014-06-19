cassandra-list-subranges
===

Lists token subtranges of a given column family. The output of this command can be used as input for subrange repair.

For more information on subrange repair, check this link: http://www.datastax.com/dev/blog/advanced-repair-techniques

# Dependencies

* Maven

# Compile

Before using the tool, compile it using the following command:

`mvn package`

# Usage

In order to print a list of <ks>.<cf> subranges from <startToken> to <endToken> with <keysPerSplit> keys:

`java -jar target/cassandra-list-subranges-0.0.1-SNAPSHOT.jar <host> <ks> <cf> <keysPerSplit> <startToken> <endToken>`

## Example

java -jar target/cassandra-list-subtranges-0.0.1-SNAPSHOT.jar cas01.myorganization.com myks mycf 131072 63802943797675961899382738893456539648 0

# Subrange repair

Use the output of the cassandra-list-subtranges as input for the repair command:

`nodetool repair <ks> <cf> -st <startToken> -et <endToken>`

## Example

`nodetool repair myks mycf -st 154281943151324548043651808333706143715 -et 156541576535381173370881008574335517403`

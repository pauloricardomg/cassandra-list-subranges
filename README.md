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

In order to print a list of subranges from `<startToken>` to `<endToken>` of the column family `<ks>`.`<cf>` containing approximately `<keysPerSplit>` keys:

`java -jar target/cassandra-list-subranges-0.0.1.jar <host> <ks> <cf> <keysPerSplit> <startToken> <endToken>`

## Example

```
java -jar target/cassandra-list-subtranges-0.0.1.jar cas01.myorganization.com myks mycf 131072 63802943797675961899382738893456539648 0
Start Token                             End Token                               Estimated Size
------------------------------------------------------------------------------------------------
63802943797675961899382738893456539648  149754238816874633134119387290723465869 143104
149754238816874633134119387290723465869 152013523477421711727827521695475332900 142848
152013523477421711727827521695475332900 154281943151324548043651808333706143715 143104
154281943151324548043651808333706143715 156541576535381173370881008574335517403 143104
156541576535381173370881008574335517403 158816604589252895925877576316314875294 143104
158816604589252895925877576316314875294 161084626211083094460372545763704903422 142848
161084626211083094460372545763704903422 163344524859451337986502817760913554356 143104
163344524859451337986502817760913554356 165610655324890521603059310862092462338 143104
165610655324890521603059310862092462338 167873178207697024732810598654736421921 142848
167873178207697024732810598654736421921 0                                       143104
```

# Subrange repair

Use the output of the cassandra-list-subtranges as input for the repair command:

`nodetool repair <ks> <cf> -st <startToken> -et <endToken>`

## Example

`nodetool repair myks mycf -st 154281943151324548043651808333706143715 -et 156541576535381173370881008574335517403`

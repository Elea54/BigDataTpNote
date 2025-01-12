package org.epf.hadoop.colfil1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class RelationshipReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> uniqueRelationships = new HashSet<>();
        for (Text val : values) {
            uniqueRelationships.add(val.toString());
        }
        StringJoiner joiner = new StringJoiner(",");
        uniqueRelationships.stream()
                .sorted()
                .forEach(joiner::add);
        context.write(key, new Text(joiner.toString()));
    }
}


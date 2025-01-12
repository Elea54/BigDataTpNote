package org.epf.hadoop.colfil2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommonFriendsReducer extends Reducer<UserPair, Text, UserPair, Text> {
    private final Text result = new Text();

    @Override
    protected void reduce(UserPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> mutualFriends = new HashSet<>();

        for (Text value : values) {
            mutualFriends.add(value.toString());
        }
        if (mutualFriends.size() > 1) {
            result.set(String.valueOf(mutualFriends.size()));
            context.write(key, result);
        }
    }
}

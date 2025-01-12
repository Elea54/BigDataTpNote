package org.epf.hadoop.colfil3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> recommendations = new ArrayList<>();
        for (Text value : values) {
            recommendations.add(value.toString());
        }
        Collections.sort(recommendations, (a, b) -> {
            int countA = Integer.parseInt(a.split(",")[1]);
            int countB = Integer.parseInt(b.split(",")[1]);
            return Integer.compare(countB, countA);
        });
        int topCount = Math.min(5, recommendations.size());
        StringBuilder topRecommendations = new StringBuilder();
        for (int i = 0; i < topCount; i++) {
            topRecommendations.append(recommendations.get(i)).append(";");
        }
        context.write(key, new Text(topRecommendations.toString()));
    }
}

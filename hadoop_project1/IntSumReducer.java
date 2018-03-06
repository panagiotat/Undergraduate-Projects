package wordcount;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

 public class IntSumReducer
       extends Reducer<Text,Text,Text,Text> {
    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Reducer.Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
       String k="";
       
      for (Text val : values) {
          
        sum ++;
        k=k+val.toString() ;
        
      }
      k=Integer.toString(sum) +" "+k;
      result.set(k);
      context.write(key, result);
    }
  }
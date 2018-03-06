package wordcount;


import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

    private Text error = new Text();
    private Text timestamp = new Text();

    public void map(Object key, Text value, Mapper.Context context
                    ) throws IOException, InterruptedException
    {
      StringTokenizer itr = new StringTokenizer(value.toString());
      
      while (itr.hasMoreTokens()) {
        error.set(itr.nextToken());
        timestamp.set(itr.nextToken()+" "+itr.nextToken()+" "+itr.nextToken()+" "+itr.nextToken()+" "+itr.nextToken()+" "+itr.nextToken());
        context.write(error,timestamp );
      }
    }
  }

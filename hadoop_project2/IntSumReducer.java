package project2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

 public class IntSumReducer
          extends Reducer<Text,IntWritable,Text,IntWritable[]  > {
    private IntWritable result = new IntWritable();
    
    
   
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
       IntWritable sum []= new IntWritable [Project2.status.length];
       ArrayWritable m = new ArrayWritable(IntWritable.class);
      for(int i=0;i<Project2.status.length;i++){
          
        
          sum[i]=(new IntWritable(0));
          
      }
      
      
      for (IntWritable val : values) {
         sum[val.get()]= new IntWritable(1) ;
       
          
      }
      
      
      context.write(key, sum);
    }
  }
package project2;





/**
 *
 * @author giorgos
 */
import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.*;

public class Project2 {
    
    public static FileStatus[] status;
  public static int t,n;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

      
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        
       DistributedCache.addCacheArchive(new URI("/user/giorgos/stopwords"), job.getConfiguration());
        DistributedCache.addCacheArchive(new URI("/user/giorgos/centers"), job.getConfiguration());
        
        job.setJarByClass(Project2.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable[].class);
      
    
        
        try{
                        FileSystem fs = FileSystem.get(new Configuration());
                        status = fs.listStatus(new Path(args[0]));
                        for (int i=0;i<status.length;i++){
                               t=i;
                            
                            MultipleInputs.addInputPath(job,  status[i].getPath(), TextInputFormat.class);
                        }
                }catch(Exception e){
                        System.out.println("File not found");
                }
        
         org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        
        
        n= Integer.parseInt(args[3]);

        Job job2 = Job.getInstance(conf, "word count");
        job2.setJarByClass(Project2.class);
        job2.setMapperClass(TokenizerMapper2.class);
        job2.setCombinerClass(IntSumReducer2.class);
        job2.setReducerClass(IntSumReducer2.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        
         org.apache.hadoop.mapreduce.lib.input.FileInputFormat.addInputPath(job2, new Path ("/user/giorgos/output/index"));
    org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        
 
System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

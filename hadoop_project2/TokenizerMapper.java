package project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper
        extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable val = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
    ) throws IOException, InterruptedException {

        Path[] uris = DistributedCache.getLocalCacheFiles(context.getConfiguration());

        val.set(Project2.t);
        try {
            
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                
                BufferedReader readBuffer1 = new BufferedReader(new FileReader(uris[0].toString()));
            String line;

                String[] words = itr.nextToken().toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                word.set(words.toString());
                boolean is=true;

                while ((line = readBuffer1.readLine()) != null) {
                    if (line.equals(word.toString())) {
                        is=false;

                    } else {
                        
                       
                    }

                }
                if(is==true){
                    
                     String str = word.toString();
                        Stemmer stemmer = new Stemmer();
                        char w[] = str.toCharArray();
                        stemmer.add(w, w.length);
                        stemmer.stem();
                        str = stemmer.toString();
                        word.set(str);

                        context.write(word, val);
                }
                readBuffer1.close();
            }

            
        } catch (Exception e) {

        }

    }
}

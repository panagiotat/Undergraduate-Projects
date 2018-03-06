package project2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper2
       extends Mapper<Object, Text, IntWritable, Text>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
        
        
         Path[] uris = DistributedCache.getLocalCacheFiles(context.getConfiguration());
         BufferedReader readBuffer = new BufferedReader(new FileReader(uris[1].toString()));
        double cent[]= new double[2*Project2.status.length];
        String r;
        ArrayList<String> words = new ArrayList();
        ArrayList<Integer> numb = new ArrayList();
        
       
      StringTokenizer itr = new StringTokenizer(value.toString());
      int i=0;
      String regex = "\\s*\\b[\\b\\s*";
      String regex1 = "\\s*\\b,\\b\\s*";
      String regex2 = "\\s*\\b]\\b\\s*";
      
      while (itr.hasMoreTokens()) {
          words.add(itr.nextToken());
          r=itr.nextToken();
        
        r = r.replaceAll(regex, "");
        while(r.contains(",")){
            
            
            r = r.replaceAll(regex, "");
            numb.add(Integer.parseInt(r));
            
        }
        r=r.replaceAll(regex2, "");
        numb.add(Integer.parseInt(r));
        
      }
      for (int h=0;h<Project2.n ;h++){
           ArrayList<Integer> team1 = new ArrayList();
        ArrayList<Integer> team2 = new ArrayList();
        
          String line;
          int m=0;
          while((line = readBuffer.readLine()) != null){
              cent[m]=Integer.parseInt(line);
              m++;
          }
          
          double s1,s2;
          for(int tr=0;tr<words.size();tr++){
              s1=1 -( (numb.get(tr*3)*cent[0] +numb.get(tr*3+1)*cent[1]+numb.get(tr*3+2)*cent[2]+numb.get(tr*3+3)*cent[3])/(Math.sqrt(numb.get(tr*3)^2+numb.get(tr*3+1)^2+numb.get(tr*3+2)^2+numb.get(tr*3+3)^2)+Math.sqrt(cent[0]*cent[0] +cent[1]*cent[1]+cent[2]*cent[2]+cent[3]*cent[3])));
               s2=1 -( (numb.get(tr*3)*cent[4] +numb.get(tr*3+1)*cent[5]+numb.get(tr*3+2)*cent[6]+numb.get(tr*3+3)*cent[7])/(Math.sqrt(numb.get(tr*3)^2+numb.get(tr*3+1)^2+numb.get(tr*3+2)^2+numb.get(tr*3+3)^2)+Math.sqrt(cent[0]*cent[0] +cent[1]*cent[1]+cent[2]*cent[2]+cent[3]*cent[3])));
          if(s1<s2){
              team1.add(tr);
          }else team2.add(tr);
          
          
          }
          int t[]=new int [8];
          
          
          for(int g1=0;g1<team1.size();g1++){
              t[0]=numb.get(g1*3)+t[0];
              t[1]=numb.get(g1*3+1)+t[1];
              t[2]=numb.get(g1*3+2)+t[2];
              t[3]=numb.get(g1*3+3)+t[3];
              
          }
          for(int g2=0;g2<team2.size();g2++){
              t[4]=numb.get(g2*3)+t[4];
              t[5]=numb.get(g2*3+1)+t[5];
              t[6]=numb.get(g2*3+2)+t[6];
              t[7]=numb.get(g2*3+3)+t[7];
              
              
          }
          
          cent[0]=t[0]/4;
         cent[1]=t[1]/4;
         cent[2]=t[2]/4;
         cent[3]=t[3]/4;
         cent[4]=t[4]/4;
         cent[5]=t[5]/4;
         cent[6]=t[6]/4;
         cent[7]=t[7]/4;
          
          
           String last1="",last2="";
           if(h== Project2.n-1){
               
           
      for(int l1=0;l1<team1.size();l1++){
          last1=last1+words.get(team1.get(l1));
          
      }
      
       for(int l2=0;l2<team1.size();l2++){
          last2=last2+words.get(team2.get(l2));
          
      }
       word.set(last1);
       context.write( new IntWritable(1),word);
       word.set(last2);
       context.write(new IntWritable(2),word);
       
           }
      
      }
      
     
      
    }
  }
import java.util
import java.util.Calendar
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.MinMaxScaler
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import Array._


object k_means {


  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
      Logger.getLogger("akka").setLevel(Level.OFF)
    println("\nWelcome to my program....!!\n|||||||||||||||             |||||||||||||||")

    //Here i read the csv name

    println("Please give the name of the csv (without the .csv)..!!")
   val points = Console.readLine()


    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("K_Means")


    val sc = new SparkContext(sparkConf) // create spark context
    val sQLContext = new SQLContext(sc)

    //Here i check if the csv exists

    try {
      sQLContext.read.option("header", "false").option("inferSchema", "true").csv(points+".csv").toDF("row1", "row2")
    } catch {

      case e: Exception => println("File not found........Terminating program!!!")
        return
    }



    //Here i read my csv and i normalize the dataset to [0-1]

    val dataframe = sQLContext.read.option("header", "false").option("inferSchema", "true").csv(points+".csv").toDF("row1", "row2")
    val dataframe1 = dataframe.filter(x => !x.anyNull)

    val assembler1 = new VectorAssembler().
      setInputCols(Array("row1")).
      setOutputCol("vec1")

    val assembler2 = new VectorAssembler().
      setInputCols(Array("row2")).
      setOutputCol("vec2")

    val assembled1 = assembler1.transform(dataframe1)
    val assembled2 = assembler2.transform(assembled1)

    val scaler1 = new MinMaxScaler()
      .setInputCol("vec1")
      .setOutputCol("scaled")

    val scaler2 = new MinMaxScaler()
      .setInputCol("vec2")
      .setOutputCol("scaled2")

    val scalerModel = scaler1.fit(assembled2)
    val scalerMode2 = scaler2.fit(assembled2)


    val scaledData = scalerModel.transform(assembled2)
    val scaledData2 = scalerMode2.transform(scaledData)


    //Does not work
   // val RDD_Vector = features.rdd.map(x=> Vectors.dense(x.getDouble(0) ,x.getDouble(1) ))

    val features = scaledData2.select("scaled", "scaled2")
    val featuresRDD = features.rdd.map(s => ((s.get(0) , s.get(1)).toString() ))
    val dataframe3 = featuresRDD.map(x => x.replace("[" , ""))
    val dataframe4 = dataframe3.map(x => x.replace("]", ""))
    val dataframe5 = dataframe4.map(x => x.replace("(", ""))
    val dataframe6 = dataframe5.map(x => x.replace(")", ""))
    val dataframe7 = dataframe6.map(line => line.split(","))
    val dataframe8 = dataframe7.map(x => x.map(_.toDouble))
   val RDD_Vector = dataframe8.map(line => Vectors.dense(line))



    //Here i calculate how many centers i have and the centers (please see the report's "1)" )

    val numIterations = 20
    var K_centers = 2
    var over = "false"
    val threshold = 0.2

    val list = new util.ArrayList[KMeansModel]

    while (
      over=="false"
    ) {
      val clusters = KMeans.train(RDD_Vector, K_centers, numIterations)


      var t=0.0
      list.add(clusters)
      if(K_centers>2){
         t= (list.get(K_centers-3).computeCost(RDD_Vector)-list.get(K_centers-2).computeCost(RDD_Vector))/list.get(K_centers-3).computeCost(RDD_Vector)
      }

      if(t<threshold && K_centers>2 ){
        over="true"
        K_centers=K_centers-1
      }else{
        K_centers=K_centers+1

      }

    }

   val clusters1 = list.get(K_centers-2)

    //Here i store the centers into Array
    val cent = new Array[Vector] (clusters1.clusterCenters.length)

    for(i<-0 to clusters1.clusterCenters.length-1){

      cent(i)=clusters1.clusterCenters(i)
    }

 // Here i calculate my main rdd

    val main_RDD = RDD_Vector.map(x=> (x,centers(clusters1,x) ) )


    //Here i calculate standard deviation for one cluster only to find outliers (please see the report's "3)" )


    val pre_standDevX = main_RDD.map(x=>( x._2(0), ( Math.pow(x._1(0) - cent(x._2(0) )(0) ,2) ,1) )).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2)  ).map(x=> (x._1,Math.sqrt( x._2._1/x._2._2 )) )

    val pre_standDevY = main_RDD.map(x=>( x._2(0), (Math.pow(x._1(1) - cent(x._2(0) )(1) ,2),1   ))).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2)  ).map(x=> (x._1,Math.sqrt( x._2._1/x._2._2 )) )




    //val pre_standDevX = main_RDD.filter(x=> x._2(0)==0).map(x=>( x._2(0), ( Math.pow(x._1(0) - cent(x._2(0) )(0) ,2) ,1) )).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2)  ).map(x=> (x._1,Math.sqrt( x._2._1/x._2._2 )) )
    //val pre_standDevY = main_RDD.filter(x=> x._2(0)==0).map(x=>( x._2(0), (Math.pow(x._1(1) - cent(x._2(0) )(1) ,2),1   ))).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2)  ).map(x=> (x._1,Math.sqrt( x._2._1/x._2._2 )) )


    //  val  standDex_X = pre_standDevX.collect()(0)._2

    // val  standDex_Y = pre_standDevY.collect()(0)._2


      //Here i calculate standard deviation for each cluster to find outliers


    val standDev =  Array.ofDim[Double] (pre_standDevX.collect().length ,2)

    for(i<-0 to pre_standDevX.collect().length-1){

      standDev(pre_standDevX.collect()(i)._1)(0)=pre_standDevX.collect()(i)._2
      standDev(pre_standDevY.collect()(i)._1)(1)=pre_standDevY.collect()(i)._2


    }




    // Here i find outliers with σ=3.5 (please see the report's "3)" )
    val threshold_standDEV= 3.5

    //val outliers = main_RDD.map(x=> (x._2(0),x._1) ).filter(x=> (Math.abs(x._2(0)-cent(x._1)(0)) > (threshold_standDEV*standDev(x._1)(0)) ) || (Math.abs(x._2(1)-cent(x._1)(1)) > (threshold_standDEV*standDev(x._1)(1)) )).map(x=> x._2)

    val outliers = main_RDD.map(x=> (x._2(0),x._1) ).filter(x=> (Math.abs(x._2(0)-cent(x._1)(0)) > (threshold_standDEV*standDev(x._1)(0) ) || (Math.abs(x._2(1)-cent(x._1)(1)) > (threshold_standDEV*standDev(x._1)(1) )))).map(x=> x._2)


    // Here i calculate AI and BI (please see the report's "2)" )

    val primary_key_A = RDD_Vector.map(x=> (centers(clusters1,x)(0),(x, centers(clusters1,x)(1))  ) )
    val primary_key_B = RDD_Vector.map(x=> (centers(clusters1,x)(1), (x, centers(clusters1,x)(0)) ) )

    val AI = primary_key_A.join(primary_key_A).map(x=>(  (x._2._2._1,x._1), Math.sqrt(Vectors.sqdist(x._2._2._1 ,x._2._1._1) ) )).reduceByKey(_+_)
    val BI = primary_key_B.join(primary_key_A).map(x=>( (x._2._1._1,x._2._1._2), ( Math.sqrt(Vectors.sqdist(x._2._2._1 ,x._2._1._1) ) ,1) )).reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2  ) )

    val joined_AI_BI = AI.join(BI).map(x=> (x._1._2, ( x._2._2._2*(x._2._2._1 -x._2._1)/Math.max(x._2._2._1,x._2._1) , x._2._2._2)) ).reduceByKey((x,y) => ( x._1 + y._1, x._2 + y._2)  ).map(x=> (x._1,x._2._1 / x._2._2 )).sortBy(_._1)


    // Here i print the number of the centers that i found

    println("The centers are " + clusters1.clusterCenters.length + " and the are: " )
    clusters1.clusterCenters.foreach(println)


    //Here i print the centers and the Silhouette of each center

    for(w<-0 to joined_AI_BI.collect().length-1){

      println(w+1 + ")Center's Silhouette is: "+  (joined_AI_BI.collect()(w)._2 ))

    }

    // Here i print the outliers
    println("There are " + outliers.collect().length +" outliers with σ=" +threshold_standDEV+ " and they are: "   )
    outliers.foreach(x=>println(x))



  }

  //In this function i find the center that my point belongs and the nearest center, and i return an array(2) (please see the report's "2)" )
  def centers(b: KMeansModel, c: Vector): Array[Int] = {
    val ar = new Array[Int] (2)

    var ce=0
   var ap2=0.0
    ar(0)= b.predict(c)


    if (b.predict(c) != 0) {


      ap2 = Math.sqrt(Vectors.sqdist(c, b.clusterCenters(0)) )
      ce = 0
    } else {



      ap2 = Math.sqrt(Vectors.sqdist(c, b.clusterCenters(1)) )
      ce = 1
    }
    for (r <- 1 to b.clusterCenters.length - 1) {
      if (ap2 > Math.sqrt(Vectors.sqdist(c, b.clusterCenters(r)) ) && b.predict(c) != r) {


        ap2 =Math.sqrt(Vectors.sqdist(c, b.clusterCenters(r)))
        ce = r
      }

      ar(1)=ce

    }





return ar
  }

}
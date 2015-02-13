package org.project.thunder.streaming.examples

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

import org.project.thunder.streaming.util.ThunderStreamingContext

object ExampleLoadStreaming {

  def main(args: Array[String]) {

    val master = args(0)

    val dataPath = args(1)

    val batchTime = args(2).toLong

    val conf = new SparkConf().setMaster(master).setAppName("ExampleLoadStreaming")

    val ssc = new StreamingContext(conf, Seconds(batchTime))

    val tssc = new ThunderStreamingContext(ssc)

    val data = tssc.loadStreamingSeries(dataPath, inputFormat="text", nKeys=2)

    val means = data.seriesMean()

    means.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
/**
 * Created with IntelliJ IDEA.
 * User: luft
 * Date: 12/1/12
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */

import com.google.monitoring.runtime.instrumentation.AllocationRecorder
import com.google.monitoring.runtime.instrumentation.Sampler


object Bench extends App {
  var objCount:Long = 0
  val s:Sampler = new Sampler() {
    def sampleAllocation(count:Int, desc:String, newObj:Object, size:Long) = {
      objCount += 1
    }
  }
  AllocationRecorder.addSampler(s)  
  def test(v: Benchmark, n: Int, times: Int) = {
    def runTest() = {
      System.gc()
      val start = System.currentTimeMillis()
      objCount = 0
      v.execute(n)
      System.currentTimeMillis() - start
    }

    def createReport(measurementType: String, data: Seq[Long]) {
      printf("\t%s results: \n\t\tAverage: \t%fms\n\t\tMedian: \t%dms\n\t\tMode: \t\t%dms\n \t\tAllocation: \t%d\n \n",
        measurementType,
        ((data sum) / data.length.toDouble),
        (data sorted) apply (data.length / 2),
        ((data map (v => (data count (v == _)) -> v)) sortBy (_._1)).last._2,
	objCount
       )   
    }

    val cold = (0 to times).map(x => runTest())
    val warm = (0 to times).map(x => runTest())

    println(v.name + "\n" + "_" * (v.name.length+10) + "\n")
    

    createReport("Cold", cold)
    createReport("Warm", warm)
  }
  
  test(Sundaram, 3000000, args(0).toInt)
  test(Eratosthenes, 75000, args(1).toInt)
  //test(ParallelPrimes, 3000000, 10)

}

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jreddypyla on 4/5/15.
 */
public class GreedyScheduler {

    private GreedyScheduler() {

    }

    private static final class GreedySchedulerHolder {
        private static final GreedyScheduler GREEDY_SCHD = new GreedyScheduler();

    }

    public static GreedyScheduler getInstance() {
        return GreedySchedulerHolder.GREEDY_SCHD;
    }



    public static void main(String args[]) {

        In in = new In(args[0]);
        int numberOfJobs = in.readInt();
        int[] jobdetails = in.readAllInts();
        JobParams[] jobParamsList = new JobParams[numberOfJobs];
        for (int i = 0; i < jobdetails.length; i = i + 2) {
            jobParamsList[i / 2] = new JobParams(jobdetails[i], jobdetails[i + 1]);
        }

        //Greedy scheduler using the Difference Cost function

        System.out.println("Time using the Difference Cost Function : " +
                GreedyScheduler.getInstance().greedyScheduler(jobParamsList, new RatioCostFunction()));


    }


    public long greedyScheduler(JobParams[] jobParamsList, Comparator<JobParams> costFunction) {
        Arrays.sort(jobParamsList, costFunction);

        long totalTime = 0;
        long runLen = 0 ;

        for (JobParams params : jobParamsList) {

            runLen = runLen + params.length;
            totalTime = totalTime + params.weight * runLen;
        }
        return totalTime;
    }


    /**
     * Created by jreddypyla on 4/5/15.
     */
    private static final class JobParams {

        private int weight;
        private int length;

        private JobParams(int weight, int length) {
            this.weight = weight;
            this.length = length;
        }

    }

    private static abstract class CostFunction implements Comparator<JobParams> {

        protected abstract double getCost(JobParams o1);

        @Override
        public int compare(JobParams o1, JobParams o2) {

            double o1diff = getCost(o1);
            double o2diff = getCost(o2);
            if (o1diff > o2diff) return -1;
            else if (o1diff == o2diff) {
                if (o1.weight > o2.weight) {
                    return -1;
                } else if (o1.weight == o2.weight) {

                    if (o1.length < o2.length) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 1;
            }
            return 1;

        }
    }

    private static final class DifferenceCostFunction extends CostFunction {

        @Override
        protected double getCost(JobParams o1) {
            return (double) (o1.weight - o1.length);
        }
    }


    private static final class RatioCostFunction extends CostFunction {

        @Override
        protected double getCost(JobParams o1) {
            return ((double)o1.weight/(double)o1.length);
        }
    }


}

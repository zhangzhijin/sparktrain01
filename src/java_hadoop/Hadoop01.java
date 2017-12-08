package java_hadoop;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.io.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.StringTokenizer;

public class Hadoop01 {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration configuration = new Configuration();
		String[] otherArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage:wordcount <in> <out>");
			System.exit(2);
		}
		

		// ������ҵ��
		Job job = new Job(configuration, "word count");
		job.setJarByClass(Hadoop01.class);
		job.setMapperClass(TokenizerMapper.class);
		
		job.setCombinerClass(IntSumReducer.class);
		
		job.setReducerClass(IntSumReducer.class);
		
		job.setOutputKeyClass(Text.class);
		
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

	/*
	 * �̳�mapper�ӿڣ�����map����������Ϊ<Object,Text>,�������Ϊ<Text,IntWritable>
	 */
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		// one��ʾ���ʳ�����һ��
		private final static IntWritable one = new IntWritable(1);
		// word�����洢�������ĵ���
		private Text word = new Text();

		// map���н����ݷָ��<���ʣ�1>����ʽwrite����
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			// ���е��ʵ��з�
			StringTokenizer itr = new StringTokenizer(value.toString());

			while (itr.hasMoreElements()) {
				word.set(itr.nextToken());// ���µĵ��ʷŵ�word��
				context.write(word, one);
			}
		}
	}

	/**
	 * reducer�����ı�д
	 * 
	 * @author Administrator
	 * 
	 */
	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		// result��¼���ʵ�Ƶ��
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

}
